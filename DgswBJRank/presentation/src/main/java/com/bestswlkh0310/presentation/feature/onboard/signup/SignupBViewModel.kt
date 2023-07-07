package com.bestswlkh0310.presentation.feature.onboard.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Security
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.domain.repository.GrassesRepository
import com.bestswlkh0310.domain.repository.UserRepository
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupBViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
): BaseViewModel() {
    val intro = MutableLiveData<String>("")
    val goal = MutableLiveData<String>("")

    val nickName = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val bjId = MutableLiveData<String>()

    fun onClickSignup() {
        add(authRepository.signUpUser(
            mapOf(
                "nickName" to nickName.value!!,
                "pw" to (Security.hashPassword(pw.value!!)),
                "bjId" to bjId.value!!,
                "intro" to intro.value!!,
                "goal" to goal.value!!
            )
        ).subscribe({ response ->
            when (response.code()) {
                200 -> {
                    viewEvent(SIGN_UP)
                    with(DgswBJRankApplication) {
                        prefs.isSignUp = true
                        prefs.nickName = nickName.value!!
                    }
                    updateAlarmToken()

                }
                204 -> viewEvent(CAN_NOT_SIGN_UP)
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }


    private fun updateAlarmToken() {
        add(userRepository.updateAlarmToken(
            mapOf(
                "nickName" to DgswBJRankApplication.prefs.nickName,
                "alarmToken" to DgswBJRankApplication.prefs.alarmToken
            )
        ).subscribe({ response ->
            when (response.code()) {
                200 -> Log.d(TAAG, "알림 토큰 전송 완료 - updateAlarmToken() called")
                404 -> Log.d(TAAG, "유저를 찾을 수 없습니다")
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))

    }

    companion object {
        const val SIGN_UP = 0
        const val CAN_NOT_SIGN_UP = 1
    }
}