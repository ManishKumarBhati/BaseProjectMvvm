package com.bmk.baseproject.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.FragmentSecondSampleBinding
import com.bmk.baseproject.util.showNotification
import timber.log.Timber

class SecondSampleFragment : BaseFragment() {
    lateinit var binding: FragmentSecondSampleBinding

    override fun getLayout() = R.layout.fragment_second_sample

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSecondSampleBinding.bind(view)
        arguments?.let {
            Timber.d(it.getString("bmk"))
        }
        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.nav_to_first_frag)
        }
        binding.btnNotify.setOnClickListener {
            requireContext().showNotification("this is a title", "this is a Body")
        }

    }

}