package com.bestswlkh0310.presentation.service

import android.util.Log
import com.bestswlkh0310.domain.repository.UserRepository
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import com.bestswlkh0310.presentation.util.NotificationHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@AndroidEntryPoint
class MessagingService: FirebaseMessagingService() {

    @Inject
    lateinit var userRepository: UserRepository

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun add(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        
        Log.d(TAAG, "$token - onNewToken() called")
        with(DgswBJRankApplication) {
            prefs.alarmToken = token
            if (prefs.isAuthToken) {
                add(userRepository.updateAlarmToken(mapOf(
                        "nickName" to prefs.id,
                        "alarmToken" to prefs.alarmToken
                    )).subscribe({ response ->
                    when (response.code()) {
                        200 -> Log.d(TAAG, "알림 토큰 전송 완료 - updateAlarmToken() called")
                        404 -> Log.d(TAAG, "유저를 찾을 수 없습니다")
                    }
                }, {
                    Log.d(TAAG, "네트워크 에러 - onNewToken() called")
                }))
            }
        }
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title: String? = remoteMessage.notification?.title
        val msg: String? = remoteMessage.notification?.body

        Log.d(TAAG, "$title - $msg - onMessageReceived() called")

        val notificationHelper = NotificationHelper(this)
        notificationHelper.sendNotification(title!!, msg!!)
    }
}