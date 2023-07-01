package com.bestswlkh0310.presentation.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DgswBJRankApplication: Application() {
    companion object {
        lateinit var prefs: PreferenceManager
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceManager(applicationContext)
    }
}