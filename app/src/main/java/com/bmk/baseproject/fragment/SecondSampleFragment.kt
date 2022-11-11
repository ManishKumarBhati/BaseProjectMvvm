package com.bmk.baseproject.fragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.bmk.baseproject.R
import com.bmk.baseproject.TimerService
import com.bmk.baseproject.databinding.FragmentSecondSampleBinding
import com.bmk.baseproject.helper.Helper
import com.bmk.domain.UserDetails
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SecondSampleFragment : BaseFragment() {
    lateinit var binding: FragmentSecondSampleBinding
    var mService: TimerService? = null

    @Inject
    lateinit var helper: Helper
    override fun getLayout() = R.layout.fragment_second_sample

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSecondSampleBinding.bind(view)
        arguments?.let {
            it.getParcelable<UserDetails>(ARGS_USER_DATA)?.let { response ->
                setView(response)
            }
            bindService()
        }

    }

    private fun bindService() {
        Intent(activity, TimerService::class.java).also { intent ->
            activity?.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun unbindService() {
        Intent(activity, TimerService::class.java).also { intent ->
            activity?.unbindService(serviceConnection)
        }
    }

    private fun setView(response: UserDetails) {
        binding.apply {
            tvEmail.text = response.email
            tvName.text = response.getFullName()
            Glide.with(this.root.context)
                .load(response.avatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivAvatar)
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, iBinder: IBinder) {
            val binder = iBinder as TimerService.LocalBinder
            mService = binder.getService()
            getRandomNumberFromService()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
        }
    }

    private fun getRandomNumberFromService() {
        mService?.randomNumberLiveData?.observe(this) {
            helper.startCounter(it)
            if (it == 0) {
                unbindService()
//                findNavController().popBackStack()
            }
        }
    }

    companion object {
        const val ARGS_USER_DATA = "user_data"
    }

}