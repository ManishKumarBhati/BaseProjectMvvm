package com.bmk.baseproject.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.FragmentSecondSampleBinding
import com.bmk.baseproject.util.showNotification
import com.bmk.domain.UserDetails
import timber.log.Timber

class SecondSampleFragment : BaseFragment() {
    lateinit var binding: FragmentSecondSampleBinding

    override fun getLayout() = R.layout.fragment_second_sample

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSecondSampleBinding.bind(view)
        arguments?.let {
            it.getParcelable<UserDetails>(ARGS_USER_DATA)?.let { response ->
                Timber.d("bmk $response")
            }
        }
        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.nav_to_first_frag)
        }
        binding.btnNotify.setOnClickListener {
            requireContext().showNotification("this is a title", "this is a Body")
        }

    }

    companion object {
        const val ARGS_USER_DATA = "user_data"
    }

}