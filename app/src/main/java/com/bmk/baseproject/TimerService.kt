package com.bmk.baseproject

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.Handler
import android.os.IBinder
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import java.util.*

class TimerService : Service() {

    private val binder = LocalBinder()

    private val mGenerator = Random()
    val randomNumberLiveData: MutableLiveData<Int> = MutableLiveData()

    val randomNumber: Int
        get() = mGenerator.nextInt(100)


    override fun onCreate() {
        super.onCreate()
        val timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                randomNumberLiveData.postValue((millisUntilFinished / 1000).toInt())
                Timber.d("counter ${millisUntilFinished / 1000}")

            }

            override fun onFinish() {
            }
        }
        timer.start()
    }

    inner class LocalBinder : Binder() {

        fun getService(): TimerService = this@TimerService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}
