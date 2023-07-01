package com.bestswlkh0310.presentation.feature.onboard.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import com.traveling.domain.repository.AuthRepository
import com.traveling.domain.repository.UserRepository
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
            viewEvent(CAN_NOT_LOGIN)
        } else {
            add(authRepository.signInUser(
                mapOf(
                    "nickName" to nickName.value,
                    "pw" to pw.value
                )
            ).subscribe({ response ->
                when (response.code()) {
                    200 -> {
                        val refreshToken = response.body()!!.refreshToken
                        val accessToken = response.body()!!.token
                        DgswBJRankApplication.prefs.refreshToken = refreshToken
                        DgswBJRankApplication.prefs.accessToken = accessToken
                        viewEvent(LOGIN)
                    }
                    500 -> viewEvent(CAN_NOT_LOGIN)
                }
            }, {
                viewEvent(NETWORK_ERROR)
            }))
        }
    }

    companion object {
        const val LOGIN = 0
        const val CAN_NOT_LOGIN = 1
    }
}