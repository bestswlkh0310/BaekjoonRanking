package com.bestswlkh0310.presentation.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(MARU_APP, Context.MODE_PRIVATE)

    var refreshToken: String by PreferenceDelegate(REFRESH_TOKEN, "")
    var accessToken: String by PreferenceDelegate(ACCESS_TOKEN, "")
    var isAuthToken: Boolean by PreferenceDelegate(IS_AUTH_TOKEN, false)
    var alarmToken: String by PreferenceDelegate(ALARM_TOKEN, "")
    var id: Int by PreferenceDelegate(NICKNAME, -1)

    fun delToken() {
        accessToken = ""
        refreshToken = ""
        isAuthToken = false
        id = -1
    }

    companion object {
        const val MARU_APP = "MARU_APP"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val IS_AUTH_TOKEN = "IS_AUTH_TOKEN"
        const val ALARM_TOKEN = "ALARM_TOKEN"
        const val IS_SIGN_UP = "IS_SIGN_UP"
        const val NICKNAME = "NICKNAME"
    }

    private inner class PreferenceDelegate<T>(
        private val key: String,
        private val defaultValue: T
    ) {
        operator fun getValue(thisRef: Any?, property: Any?): T {
            return when (defaultValue) {
                is String -> prefs.getString(key, defaultValue as String) as T
                is Boolean -> prefs.getBoolean(key, defaultValue as Boolean) as T
                is Int -> prefs.getInt(key, defaultValue as Int) as T
                else -> throw IllegalArgumentException("Unsupported preference type")
            }
        }

        operator fun setValue(thisRef: Any?, property: Any?, value: T) {
            when (value) {
                is String -> prefs.edit().putString(key, value as String).apply()
                is Boolean -> prefs.edit().putBoolean(key, value as Boolean).apply()
                is Int -> prefs.edit().putInt(key, value as Int).apply()
                else -> throw IllegalArgumentException("Unsupported preference type")
            }
        }
    }
}
