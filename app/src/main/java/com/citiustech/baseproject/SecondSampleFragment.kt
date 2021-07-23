package com.citiustech.baseproject

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.citiustech.baseproject.base.BaseFragment
import com.citiustech.baseproject.databinding.FragmentSecondSampleBinding
import timber.log.Timber

class SecondSampleFragment : BaseFragment() {
    lateinit var binding: FragmentSecondSampleBinding

    override fun getLayout() = R.layout.fragment_second_sample

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSecondSampleBinding.bind(view)
        arguments?.let {
            Timber.d(it.getString("bmk"))
        }
        binding.tvText.setOnClickListener {
//            PreferenceManager(requireContext()).setPreferredLocale("en")
//            activity?.recreate()
            findNavController().navigate(R.id.nav_to_first_frag)
        }
    }

}