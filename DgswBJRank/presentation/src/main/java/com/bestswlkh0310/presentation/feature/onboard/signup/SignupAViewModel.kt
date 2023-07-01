package com.bestswlkh0310.presentation.feature.onboard.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.traveling.domain.repository.AuthRepository
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

    fun onClickCheck() {
        add(authRepository.checkDuplicateBjId(bjId.value!!).subscribe({ response ->
            when (response.code()) {
                200 -> {
                    bjIdDetail.value = "그런 아이디는 있네요"
                }
                204 -> {
                    viewEvent(NOT_FOUND_BJ_ID)
                }
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    companion object {
        const val NOT_FOUND_BJ_ID = 0
        const val FOUND_BJ_ID = 1
    }
}