package com.citiustech.baseproject.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.citiustech.baseproject.R
import com.citiustech.baseproject.databinding.ActivityMainBinding
import com.citiustech.baseproject.helper.Helper
import com.citiustech.baseproject.helper.NetworkHelper
import com.citiustech.baseproject.helper.WorkManagerTag
import com.citiustech.baseproject.services.MyWorkManager
import com.citiustech.data.network.SessionManager
import com.citiustech.domain.Repository
import com.citiustech.domain.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
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
        test()
        isRefreshRequired()
        networkCheck()
        calculateArea(5)
    }

    private fun test() {
        val constrain = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)

        val timeDiff = 1629805020000 - Date().time

        val dailyWorkRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
            .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
            .setConstraints(constrain.build())
            .addTag(WorkManagerTag)
            .build()

        WorkManager.getInstance(application)
            .enqueue(dailyWorkRequest)

    }


    private fun networkCheck() {
        networkHelper.observe(this) {
            if (!it) showShortToast(getString(R.string.error_msg_network))
        }

    }

    private fun isRefreshRequired() {
        if (sessionManager.getRefreshToken() <= Date().time) {
            lifecycleScope.launch {
                when (val result =
                    withContext(Dispatchers.IO) { repository.refresh() }) {
                    is Result.Failure -> {
                        showError(result.exception.localizedMessage)
                    }
                    is Result.Success -> Timber.d("${result.data}")
                }
            }
        }
    }

    fun calculateArea(radius: Int): Double {
        return Math.PI * radius * radius
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
