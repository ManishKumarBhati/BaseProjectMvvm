package com.bmk.baseproject.activity

import android.os.Bundle
import android.view.View
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.ActivityMainBinding
import com.bmk.baseproject.helper.Helper
import com.bmk.baseproject.helper.NetworkHelper
import com.bmk.data.network.SessionManager
import com.bmk.domain.Repository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), Helper {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        networkCheck()

    }


    private fun networkCheck() {
        networkHelper.observe(this) {
            if (!it) showShortToast(getString(R.string.error_msg_network))
        }

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

    override fun showError(msg: String?) {
        showShortToast(msg ?: getString(R.string.error_msg_common))
    }

}
