package com.citiustech.baseproject.sample

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.citiustech.baseproject.R
import com.citiustech.baseproject.base.BaseFragment
import com.citiustech.baseproject.databinding.FragmentSampleBinding
import com.citiustech.baseproject.util.PreferenceManager
import com.citiustech.baseproject.util.Status
import com.citiustech.domain.Repository
import com.citiustech.domain.Response
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SampleFragment : BaseFragment() {
    lateinit var binding: FragmentSampleBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var repository: Repository

    private val viewModel: SampleViewModel by viewModels()

    override fun getLayout(): Int {
        return R.layout.fragment_sample
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSampleBinding.bind(view)
        setSharedPref()
        binding.tvText.setOnClickListener {
            viewModel.getData().observe(viewLifecycleOwner, {
                when (it.responseType) {
                    Status.ERROR -> it.error?.message?.let { Timber.d("bmk Errro $it") }
                    Status.LOADING -> Timber.d("bmk loader show")
                    Status.SUCCESSFUL -> it.data?.let { data ->
                        if (data is Response) Timber.d("bmk data $it")
                    }
                }
            })
        }
        binding.tvText2.setOnClickListener {
            viewModel.getLocalData().observe(viewLifecycleOwner, {
                binding.apply {
                    if (it.id > 0) {
                        tvId.text = "id:${it.id}"
                        tvStaus.text = "status:${it.completed}"
                        tvTitle.text = "title:${it.title}"
                    } else
                        tvStaus.text = "${it.title}"
                }
                Timber.d("bmk Load local Data $it")
            })
        }

        binding.btnNav.setOnClickListener {
            PreferenceManager(requireContext()).setPreferredLocale("hi")
            activity?.recreate()
            findNavController().navigate(
                R.id.nav_to_second_frag,
                bundleOf("bmk" to "Manish")
            )
        }
    }

    private fun setSharedPref() {
        sharedPreferences.edit().putString("citiustech", "test").apply()
        val d = sharedPreferences.getString("citiustech", "manish")
        Timber.d("bmk $d")
    }
}