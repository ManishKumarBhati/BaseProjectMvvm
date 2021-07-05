package com.citiustech.baseproject.navigator

import androidx.fragment.app.Fragment
import com.citiustech.baseproject.sample.SampleFragment

object FragmentGetter {
    fun getFragment(page: String): Fragment {
        return when (page) {
            NavigationConstant.NavigateToSampleFragment->SampleFragment()
            else -> SampleFragment()
        }
    }
}

object NavigationConstant {
    const val NavigateToSampleFragment = "SampleFragment"
}