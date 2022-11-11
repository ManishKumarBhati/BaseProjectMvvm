package com.bmk.baseproject.fragment

import android.os.Bundle
import android.view.View
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.FragmentSecondSampleBinding
import com.bmk.domain.UserDetails
import com.bumptech.glide.Glide

class SecondSampleFragment : BaseFragment() {
    lateinit var binding: FragmentSecondSampleBinding

    override fun getLayout() = R.layout.fragment_second_sample

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSecondSampleBinding.bind(view)
        arguments?.let {
            it.getParcelable<UserDetails>(ARGS_USER_DATA)?.let { response ->
                setView(response)
            }
        }


    }

    private fun setView(response: UserDetails) {
        binding.apply {
            tvEmail.text = response.email
            tvName.text = response.getFullName()
            Glide.with(this.root.context)
                .load(response.avatar)
                .into(binding.ivAvatar)
        }
    }

    companion object {
        const val ARGS_USER_DATA = "user_data"
    }

}