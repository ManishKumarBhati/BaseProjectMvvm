package com.bmk.baseproject.di

import android.app.Activity
import com.bmk.baseproject.helper.Helper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Provides
    fun provideActivityHelper(activity: Activity): Helper {
        return activity as Helper
    }
}