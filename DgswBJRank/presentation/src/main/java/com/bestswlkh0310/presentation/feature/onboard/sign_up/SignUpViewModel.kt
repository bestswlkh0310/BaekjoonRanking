package com.bestswlkh0310.presentation.feature.onboard.sign_up

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Security.isPasswordValid
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.presentation.util.Security
import com.bestswlkh0310.presentation.util.Security.hashPassword
import com.bestswlkh0310.presentation.util.Security.isNickNameValid
import com.bestswlkh0310.presentation.util.Security.isUserIdValid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {
    val userId = MutableLiveData<String>("")
    val nickName = MutableLiveData<String>("")
    val pw = MutableLiveData<String>("")

    fun onClickComplete() {
        if (userId.value == "" || nickName.value == "" || pw.value == "") viewEvent(WRONG_INPUT)
        else if (!isNickNameValid(nickName.value!!)) viewEvent(WRONG_NICKNAME)
        else if (!isUserIdValid(userId.value!!)) viewEvent(WRONG_ID)
        else if (!isPasswordValid(pw.value!!)) viewEvent(WRONG_PW)
        else {
            add(authRepository.signUpUser(mapOf(
                "userId" to userId.value,
                "nickName" to nickName.value,
                "pw" to hashPassword(pw.value!!)
            )).subscribe({ response ->
                when (response.code()) {
                    200 -> viewEvent(SIGN_UP)
                    204 -> viewEvent(EXIST_USER_ID)
                    404 -> viewEvent(WRONG_INPUT)
                }
            }, {
                viewEvent(NETWORK_ERROR)
            }))
        }
    }

    fun onClickSignIn() {
        viewEvent(ON_CLICK_SIGN_IN)
    }

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    companion object {
        const val WRONG_ID = 1
        const val WRONG_PW = 2
        const val WRONG_NICKNAME = 3
        const val WRONG_INPUT = 4
        const val ON_CLICK_SIGN_IN = 5
        const val SIGN_UP = 6
        const val EXIST_USER_ID = 7
        const val ON_CLICK_BACK = 8
    }
}