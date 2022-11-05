package com.bmk.baseproject

import com.bmk.baseproject.activity.MainActivity
import com.bmk.baseproject.fragment.SampleFragment
import junit.framework.Assert.assertNotNull
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
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
    fun checkToastOnLoadOfMainActivity() {
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Hello Guest"))
    }

    @Test
    @Throws(Exception::class)
    fun shouldFragmentNotBeNull() {
        val fragment = SampleFragment()
        startFragment(fragment)
        assertNotNull(fragment)
    }

    private fun startFragment(fragment: SampleFragment) {
        val fragmentManager = activity.supportFragmentManager;
        val fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }
}