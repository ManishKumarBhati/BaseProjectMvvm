package com.bmk.baseproject.helper

import android.content.Context
import android.content.SharedPreferences

class LocalizationHelper(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(Localization, Context.MODE_PRIVATE)

    fun getPreferredLocale(): String {
        return preferences.getString(PREF_LOCALIZATION, English) ?: English
    }

    fun setPreferredLocale(localeCode: String) {
        preferences.edit().putString(PREF_LOCALIZATION, localeCode).apply()
    }

    companion object {
        const val PREF_LOCALIZATION = "localization"
        const val Hindi = "hi"
        const val English = "en"
        const val Localization = "localization"
    }
}