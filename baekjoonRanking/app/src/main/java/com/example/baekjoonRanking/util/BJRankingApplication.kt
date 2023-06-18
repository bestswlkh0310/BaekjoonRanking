package com.example.baekjoonRanking.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BJRankingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}