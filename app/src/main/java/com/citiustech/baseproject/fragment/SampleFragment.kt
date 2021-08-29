package com.citiustech.baseproject.fragment

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.citiustech.baseproject.R
import com.citiustech.baseproject.databinding.FragmentSampleBinding
import com.citiustech.baseproject.helper.Helper
import com.citiustech.baseproject.helper.LocalizationHelper
import com.citiustech.baseproject.util.Status
import com.citiustech.baseproject.viewmodel.SampleViewModel
import com.citiustech.domain.Repository
import com.citiustech.domain.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.Executor
import javax.inject.Inject

@AndroidEntryPoint
class SampleFragment : BaseFragment() {
    lateinit var binding: FragmentSampleBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var repository: Repository

    private val viewModel: SampleViewModel by viewModels()
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @Inject
    lateinit var helper: Helper
    override fun getLayout(): Int {
        return R.layout.fragment_sample
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSampleBinding.bind(view)

        setSharedPref()

        checkBiometricAvailable()
        handleBiometric()

        binding.btnLoad.setOnClickListener {
            viewModel.getData().observe(viewLifecycleOwner, {
                when (it.responseType) {
                    Status.ERROR -> it.error?.message?.let { helper.showError(it) }
                    Status.LOADING -> helper.showProgress()
                    Status.SUCCESSFUL -> it.data?.let { data ->
                        helper.hideProgress()
                        showToast("data loaded for id ${data.id}")
                    }
                }
            })
        }
        binding.btnDelete.setOnClickListener {
            if (getID().isNotEmpty()) viewModel.delete(getID().toInt())
            else showToast("no id available")
        }
        binding.btnUpdate.setOnClickListener {
            if (getID() != "") viewModel.updateData(getID().toInt(), getTitle())
            else showToast("no id available")
        }
        binding.btnFetch.setOnClickListener {
            viewModel.getLocalData().observe(viewLifecycleOwner, {
                binding.apply {
                    if (it.id > 0) {
                        tvId.text = "id:${it.id}"
                        tvStaus.text = "status:${it.completed}"
                        etId.setText("${it.id}")
                        tvTitle.text = "title:${it.title}"
                    } else {
                        tvId.text = ""
                        etId.setText("")
                        tvStaus.text = "${it.title}"
                        tvTitle.text = ""
                    }
                }
            })
        }

        binding.btnLocal.setOnClickListener {
            val pref = LocalizationHelper(requireContext())
            pref.setPreferredLocale(
                if (pref.getPreferredLocale() == LocalizationHelper.English)
                    LocalizationHelper.Hindi else LocalizationHelper.English
            )
            activity?.recreate()
        }
        binding.btnNav.setOnClickListener {

            findNavController().navigate(
                R.id.nav_to_second_frag,
                bundleOf("bmk" to "Manish")
            )
        }
    }

    fun getID() = binding.etId.text.toString().trim()
    fun getTitle() = binding.etTitle.text.toString().trim()

    private fun setSharedPref() {
        sharedPreferences.edit().putString("citiustech", "test").apply()
        val d = sharedPreferences.getString("citiustech", "manish")
        Timber.d("bmk $d")
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun checkBiometricAvailable() {
        val biometricManager = BiometricManager.from(requireContext())
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                showToast(getString(R.string.msg_auth_biometric_support))
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                showToast(getString(R.string.msg_auth_biometric_unsupported))
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                showToast(getString(R.string.msg_auth_biometric_unavailable))
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
                showToast(
                    "Prompts the user to create credentials that your app accepts.."
                )
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                    )
                }
                startActivityForResult.launch(enrollIntent)
            }
        }
    }

    val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                Timber.e("Received result", " data" + result.data)
            }
        }

    private fun handleBiometric() {
        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(
            this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    showToast("Authentication error: $errString")
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    showToast(getString(R.string.msg_auth_success))
                    lifecycleScope.launch {
                        when (val result =
                            withContext(Dispatchers.IO) { repository.login("", "") }) {
                            is Result.Failure -> Timber.d(result.exception)
                            is Result.Success -> Timber.d("${result.data}")
                        }
                    }

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    showToast(getString(R.string.msg_auth_fail))

                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.msg_biometric_login))
            .setSubtitle(getString(R.string.msg_biometric_login_credential))
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            //If using setAllowedAuthenticators hence commented
            // need setNegativeButtonText when running on API 10 & above
            .setNegativeButtonText(getString(R.string.btn_use_ac_password))
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.

        binding.btnLogin.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

}