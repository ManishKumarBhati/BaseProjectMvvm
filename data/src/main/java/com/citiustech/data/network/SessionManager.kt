package com.citiustech.data.network

import android.content.SharedPreferences
import androidx.core.content.edit
import com.citiustech.data.util.REFRESH_TOKEN
import com.citiustech.data.util.USER_TOKEN
import javax.inject.Inject

class SessionManager @Inject constructor(protected val pref: SharedPreferences) {
    fun saveAuthToken(token: String, ref: Long) {
        pref.edit {
            putString(USER_TOKEN, token)
            putLong(REFRESH_TOKEN, ref)
        }
    }

    fun fetchAuthToken(): String? {
        return pref.getString(USER_TOKEN, "")
    }

    fun getRefreshToken(): Long {
        return pref.getLong(REFRESH_TOKEN, 0)
    }
}