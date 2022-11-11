package com.bmk.baseproject.activity

import android.os.Bundle
import android.view.View
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.ActivityMainBinding
import com.bmk.baseproject.helper.Helper
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity(), Helper {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun toggleProgress(show: Boolean) {
        if (show) showProgress()
        else hideProgress()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showError(throwable: Throwable) {
        Timber.e(throwable)
        showShortToast(throwable.message ?: getString(R.string.error_msg_common))
    }

}
