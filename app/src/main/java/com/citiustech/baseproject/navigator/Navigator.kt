package com.citiustech.baseproject.navigator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

interface Navigator {
    fun openPage(pageId: String)
    fun openPage(pageId: String, bundle: Bundle)
}

class NavigatorImpl @Inject constructor(val activity: FragmentActivity) : Navigator {
    override fun openPage(pageId: String) {
        openScreen(FragmentGetter.getFragment(pageId))
    }

    override fun openPage(pageId: String, bundle: Bundle) {
        openScreen(FragmentGetter.getFragment(pageId).apply { arguments = bundle })
    }

    fun openScreen(fragment: Fragment) {
//        activity.supportFragmentManager.beginTransaction()
//            .disallowAddToBackStack()
//            .replace(
//                R.id.frame, fragment, SampleFragment.TAG
//            )
//            .commit()
    }
}