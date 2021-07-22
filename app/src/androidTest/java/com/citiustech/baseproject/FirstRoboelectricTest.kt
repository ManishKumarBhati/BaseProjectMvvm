package com.citiustech.baseproject

import android.R
import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast


@RunWith(RobolectricTestRunner::class)
class FirstRoboelectricTest {

    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun checkToastOnLoadOfMainActivity(){
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Hello Guest"))
    }
}