package com.bestswlkh0310.presentation.feature.onboard.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import com.bestswlkh0310.presentation.util.Security
import com.bestswlkh0310.presentation.util.Security.isPasswordValid
import com.bestswlkh0310.presentation.util.Security.isUsernameValid
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.domain.repository.UserRepository
import com.bestswlkh0310.presentation.feature.onboard.signup.SignupAViewModel.Companion.WRONG_INPUT
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.google.firebase.analytics.FirebaseAnalytics.Event.LOGIN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
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
            add(authRepository.signInUser(
                mapOf(
                    "nickName" to nickName.value,
                    "pw" to Security.hashPassword(pw.value!!),
                )
            ).subscribe({ response ->
                when (response.code()) {
                    200 -> {
                        val refreshToken = response.body()!!.refreshToken
                        val accessToken = response.body()!!.token
                        Log.d(TAAG, "$refreshToken\n$accessToken - onClickLogin() called")
                        with(DgswBJRankApplication) {
                            prefs.refreshToken = refreshToken
                            prefs.accessToken = accessToken
                            prefs.isAuthToken = true
                        }
                        viewEvent(CHECK_ALARM_PERMISSION)
                    }
                    404 -> viewEvent(NOT_FOUND_USER)
                }
            }, {
                viewEvent(NETWORK_ERROR)
            }))
        }
    }

    fun updateAlarmToken() {
        // TODO: update Alarm Token to Server
    }

    companion object {
        const val NOT_FOUND_USER = 1
        const val WRONG_INPUT = 3
        const val CHECK_ALARM_PERMISSION = 4
    }
}