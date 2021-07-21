package com.citiustech.baseproject.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences("sp", Context.MODE_PRIVATE)

    fun getPreferredLocale(): String {
        return preferences.getString("preferred_locale", "en") ?: "en"
    }

    fun setPreferredLocale(localeCode: String) {
        preferences.edit().putString("preferred_locale", localeCode).apply()
    }
}