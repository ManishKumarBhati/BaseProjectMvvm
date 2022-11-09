package com.bmk.baseproject.fragment

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricPrompt
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.FragmentSampleBinding
import com.bmk.baseproject.helper.Helper
import com.bmk.baseproject.util.Status
import com.bmk.baseproject.viewmodel.SampleViewModel
import com.bmk.domain.Repository
import dagger.hilt.android.AndroidEntryPoint
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

        binding.btnLoad.setOnClickListener {
            viewModel.getData().observe(viewLifecycleOwner) {
                when (it.responseType) {
                    Status.ERROR -> it.error?.message?.let { helper.showError(it) }
                    Status.LOADING -> helper.showProgress()
                    Status.SUCCESSFUL -> it.data?.let { data ->
                        helper.hideProgress()
                        showToast("data loaded for id ${data.id}")
                    }
                }
            }
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
            viewModel.getLocalData().observe(viewLifecycleOwner) {
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
            }
        }

        binding.btnLocal.setOnClickListener {
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


}