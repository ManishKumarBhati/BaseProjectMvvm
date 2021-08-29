package com.citiustech.baseproject.activity

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.citiustech.baseproject.helper.LocalizationHelper
import com.citiustech.baseproject.helper.LocalizationWrapper
import java.util.*

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val localeToSwitchTo = LocalizationHelper(newBase).getPreferredLocale()
        val localeUpdatedContext: ContextWrapper =
            LocalizationWrapper.updateLocale(newBase, Locale(localeToSwitchTo))
        super.attachBaseContext(localeUpdatedContext)
    }

    fun showShortToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun handleCrash() {
        // TODO(handle crashlytics)
    }

}