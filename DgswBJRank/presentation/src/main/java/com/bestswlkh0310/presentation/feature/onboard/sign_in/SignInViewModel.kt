package com.bestswlkh0310.presentation.feature.onboard.sign_in

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import com.bestswlkh0310.presentation.util.Security
import com.bestswlkh0310.presentation.util.Security.isPasswordValid
import com.bestswlkh0310.presentation.util.Security.isUsernameValid
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.domain.repository.GrassesRepository
import com.bestswlkh0310.domain.repository.UserRepository
import com.bestswlkh0310.presentation.util.Constant.TAAG
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val grassesRepository: GrassesRepository,
    private val userRepository: UserRepository
): BaseViewModel() {

    val nickName = MutableLiveData<String>("")
    val nickNameState = MutableLiveData<Boolean>(false)

    val pw = MutableLiveData<String>("")
    val pwState = MutableLiveData<Boolean>(false)

    fun onClickLogin() {
        if (nickName.value!! == "" || pw.value!! == "") {
            viewEvent(WRONG_INPUT)
        } else if (!isPasswordValid(pw.value!!) || !isUsernameValid(nickName.value!!)) {
            viewEvent(NOT_FOUND_USER)
        } else {
            add(authRepository.signInUser(mapOf(
                    "userId" to nickName.value,
                    "pw" to Security.hashPassword(pw.value!!),
                )).subscribe({ response ->
                when (response.code()) {
                    200 -> {
                        val refreshToken = response.body()!!.refreshToken
                        val accessToken = response.body()!!.accessToken
                        Log.d(TAAG, "$refreshToken\n$accessToken - onClickLogin() called")
                        with(DgswBJRankApplication) {
                            prefs.refreshToken = refreshToken
                            prefs.accessToken = accessToken
                            prefs.isAuthToken = true
                            prefs.isSignUp = true
                            prefs.nickName = nickName.value!!
                        }
                        updateAlarmToken()
                        viewEvent(CHECK_ALARM_PERMISSION)
                    }
                    404 -> viewEvent(NOT_FOUND_USER)
                }
            }, {
                viewEvent(NETWORK_ERROR)
            }))
        }
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

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickSignUp() {
        viewEvent(ON_CLICK_SIGN_UP)
    }

    companion object {
        const val NOT_FOUND_USER = 1
        const val WRONG_INPUT = 3
        const val CHECK_ALARM_PERMISSION = 4
        const val ON_CLICK_BACK = 5
        const val ON_CLICK_SIGN_UP = 6
    }
}