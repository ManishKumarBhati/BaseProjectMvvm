package com.citiustech.baseproject.sample

import android.os.Bundle
import android.view.View
import com.citiustech.baseproject.R
import com.citiustech.baseproject.base.BaseFragment
import com.citiustech.baseproject.databinding.FragmentSampleBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

class SampleFragment : BaseFragment() {
    lateinit var binding: FragmentSampleBinding
    override fun getLayout(): Int {
        return R.layout.fragment_sample
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSampleBinding.bind(view)
        Timber.d("bmk ${arguments?.getString("bmk")}")
    }

    companion object {
        const val TAG = "SapmleFrag"
        fun newInstance(): SampleFragment {
            return SampleFragment()
        }
    }
}