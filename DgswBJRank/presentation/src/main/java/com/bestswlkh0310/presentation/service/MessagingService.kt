package com.bestswlkh0310.presentation.service

import android.util.Log
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import com.bestswlkh0310.presentation.util.NotificationHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MessagingService @Inject constructor(): FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        
        Log.d(TAAG, "$token - onNewToken() called")
        DgswBJRankApplication.prefs.alarmToken = token
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title: String? = remoteMessage.notification?.title
        val msg: String? = remoteMessage.notification?.body

        Log.d(TAAG, "$title - $msg - onMessageReceived() called")

        val notificationHelper = NotificationHelper(this)
        notificationHelper.sendNotification(title!!, msg!!)
    }
}