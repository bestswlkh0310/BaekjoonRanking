package com.bestswlkh0310.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Common {
    fun getTodayDate() = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
}