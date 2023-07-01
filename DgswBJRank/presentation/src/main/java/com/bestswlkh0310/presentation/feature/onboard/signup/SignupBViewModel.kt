package com.bestswlkh0310.presentation.feature.onboard.signup

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Security
import com.bestswlkh0310.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupBViewModel @Inject constructor(
    private val authRepository: AuthRepository
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
                200 -> viewEvent(SIGN_UP)
                204 -> viewEvent(CAN_NOT_SIGN_UP)
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