package com.bestswlkh0310.presentation.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(MARU_APP, Context.MODE_PRIVATE)

    var refreshToken: String
        get() = prefs.getString(REFRESH_TOKEN, "").toString()
        set(value) = prefs.edit().putString(REFRESH_TOKEN, value).apply()

    var accessToken: String
        get() = prefs.getString(ACCESS_TOKEN, "").toString()
        set(value) = prefs.edit().putString(ACCESS_TOKEN, value).apply()

    fun delToken() {
        accessToken = ""
        refreshToken = ""
    }
    companion object {
        const val MARU_APP = "MARU_APP"

        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"

    }
}