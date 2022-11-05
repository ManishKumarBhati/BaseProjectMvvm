package com.bmk.baseproject.services

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import timber.log.Timber


class MyWorkManager(
    ctx: Context,
    params: WorkerParameters

) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {

        return try {
            Timber.d("bmk worker")
            Result.success()
        } catch (error: Throwable) {
            Timber.e(error, "Error While refreshing token")
            Result.failure()
        }
    }
}