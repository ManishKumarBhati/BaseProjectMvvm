package com.citiustech.data.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.security.AlgorithmParameters
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class Encryptor(val context: Context, val prefs: SharedPreferences) {
    val HEX_CHARS = "0123456789ABCDEF".toCharArray()
    private var rawByteKey: ByteArray? = null
    private var dbCharKey: CharArray? = null

    fun getCharKey(passcode: CharArray): CharArray {
        if (dbCharKey == null) passcode.initKey()
        return dbCharKey ?: error("Failed to decrypt database key")
    }

    private fun CharArray.initKey() {
        val storable = getStorable()
        if (storable == null) {
            createNewKey()
            persistRawKey(this, prefs)
        } else {
            rawByteKey = getRawByteKey(storable)
            dbCharKey = rawByteKey?.toHex()
        }
    }

    private fun createNewKey() {
        // This is the raw key that we'll be encrypting + storing
        rawByteKey = generateRandomKey()
        // This is the key that will be used by Room
        dbCharKey = rawByteKey?.toHex()
    }

    private fun generateRandomKey(): ByteArray =
        ByteArray(32).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SecureRandom.getInstanceStrong().nextBytes(this)
            } else {
                SecureRandom().nextBytes(this)
            }
        }

    private fun ByteArray.toHex(): CharArray {
        val result = StringBuilder()
        forEach {
            val octet = it.toInt()
            val firstIndex = (octet and 0xF0).ushr(4)
            val secondIndex = octet and 0x0F
            result.append(HEX_CHARS[firstIndex])
            result.append(HEX_CHARS[secondIndex])
        }
        return result.toString().toCharArray()
    }

    private fun persistRawKey(userPasscode: CharArray, prefs: SharedPreferences) {
        userPasscode.toStorable()?.let {
            Timber.d("bmk data $it")
            saveToPrefs(it, prefs)
        }
    }

    private fun saveToPrefs(keyData: KeyData, prefs: SharedPreferences) {
        val serialized = Gson().toJson(keyData)
        prefs.edit().putString("key", serialized).apply()
    }

    /**
     * Returns a [KeyData] instance with the db key encrypted using PBE.
     *
     * @param rawDbKey the raw database key
     * @param userPasscode the user's passcode
     * @return storable instance
     */
    private fun CharArray.toStorable(): KeyData? {
        // Generate a random 8 byte salt
        return try {
            val salt = ByteArray(8).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    SecureRandom.getInstanceStrong().nextBytes(this)
                } else {
                    SecureRandom().nextBytes(this)
                }
            }
            val secret: SecretKey = generateSecretKey(salt)

            // Now encrypt the database key with PBE(Passcode Base Encryption)
            val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secret)
            val params: AlgorithmParameters = cipher.parameters

            val iv: ByteArray = params.getParameterSpec(IvParameterSpec::class.java).iv
            val ciphertext: ByteArray = cipher.doFinal(iv)

            // Return the IV and CipherText which can be stored to disk
            KeyData(
                Base64.encodeToString(iv, Base64.DEFAULT),
                Base64.encodeToString(ciphertext, Base64.DEFAULT),
                Base64.encodeToString(salt, Base64.DEFAULT)
            )
        } catch (e: Exception) {
            Timber.d("bmk exception $e")
            null
        }
    }

    private fun CharArray.generateSecretKey(salt: ByteArray): SecretKey {
        // Initialize PBE with password
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec: KeySpec = PBEKeySpec(this, salt, 65536, 256)
        val tmp: SecretKey = factory.generateSecret(spec)
        return SecretKeySpec(tmp.encoded, "AES")
    }


    private fun getStorable(): KeyData? {
        val data = prefs.getString("key", null)
        if (data.isNullOrBlank()) {
            return null
        }

        return try {
            Gson().fromJson(data, object : TypeToken<KeyData>() {}.type)
        } catch (ex: JsonSyntaxException) {
            null
        }
    }

    private fun CharArray.getRawByteKey(keyData: KeyData): ByteArray {
        val aesWrappedKey = Base64.decode(keyData.key, Base64.DEFAULT)
        val iv = Base64.decode(keyData.iv, Base64.DEFAULT)
        val salt = Base64.decode(keyData.salt, Base64.DEFAULT)
        val secret: SecretKey = generateSecretKey(salt)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secret, IvParameterSpec(iv))
        return cipher.doFinal(aesWrappedKey)
    }

}

data class KeyData(@JvmField val iv: String, @JvmField val key: String, @JvmField val salt: String)
