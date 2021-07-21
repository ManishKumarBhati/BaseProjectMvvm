package com.citiustech.baseproject.base

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import com.citiustech.baseproject.util.ContextUtils
import com.citiustech.baseproject.util.PreferenceManager
import java.util.*

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val localeToSwitchTo = PreferenceManager(newBase).getPreferredLocale()
        val localeUpdatedContext: ContextWrapper =
            ContextUtils.updateLocale(newBase, Locale(localeToSwitchTo))
        super.attachBaseContext(localeUpdatedContext)
    }

}