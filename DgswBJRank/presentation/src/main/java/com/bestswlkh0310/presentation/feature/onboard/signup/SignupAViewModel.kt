package com.bestswlkh0310.presentation.feature.onboard.signup

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Security.isPasswordValid
import com.bestswlkh0310.presentation.util.Security.isUsernameValid
import com.bestswlkh0310.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupAViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {
    val nickName = MutableLiveData<String>("")
    val nickNameState = MutableLiveData<Boolean>(false)

    val bjId = MutableLiveData<String>("")
    val bjIdState = MutableLiveData<Boolean>(false)

    val bjIdDetail = MutableLiveData<String>("")

    val pw = MutableLiveData<String>("")
    val pwState = MutableLiveData<Boolean>(false)

    fun onClickNext() {
        if (pw.value == "" || nickName.value == "") {
            viewEvent(WRONG_INPUT)
        } else if (!isUsernameValid(nickName.value!!)) {
            viewEvent(WRONG_ID)
        } else if (!isPasswordValid(pw.value!!)) {
            viewEvent(WRONG_PW)
        } else {
            add(authRepository.checkDuplicateBjId(bjId.value!!).subscribe({ response ->
                when (response.code()) {
                    200 -> {
                        viewEvent(FOUND_BJ_ID)
                    }
                    204 -> {
                        viewEvent(NOT_FOUND_BJ_ID)
                    }
                }
            }, {
                viewEvent(NETWORK_ERROR)
            }))
        }
    }

    fun onClickCheck() {
        add(authRepository.checkDuplicateBjId(bjId.value!!).subscribe({ response ->
            when (response.code()) {
                200 -> bjIdDetail.value = "그런 아이디는 있네요"
                204 -> viewEvent(NOT_FOUND_BJ_ID)
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    companion object {
        const val NOT_FOUND_BJ_ID = 0
        const val FOUND_BJ_ID = 1
        const val WRONG_PW = 2
        const val WRONG_ID = 3
        const val WRONG_INPUT = 4
    }
}