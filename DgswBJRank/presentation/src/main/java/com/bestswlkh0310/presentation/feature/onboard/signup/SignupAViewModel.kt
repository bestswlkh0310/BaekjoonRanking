package com.bestswlkh0310.presentation.feature.onboard.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.traveling.domain.usecase.UserUseCase
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.traveling.domain.request.VerifyRequest
import com.traveling.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupAViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val authUseCase: AuthUseCase
): BaseViewModel() {
    val nickName = MutableLiveData<String>("")
    val nickNameState = MutableLiveData<Boolean>(false)

    val bjId = MutableLiveData<String>("")
    val bjIdState = MutableLiveData<Boolean>(false)

    val bjIdDetail = MutableLiveData<String>("")
    val bjIdCheckState = MutableLiveData<Boolean>(false)

    val pw = MutableLiveData<String>("")
    val pwState = MutableLiveData<Boolean>(false)

    fun onClickNext() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                authUseCase.verify(VerifyRequest(bjId.value!!))
                withContext(Dispatchers.Main) {
                    viewEvent(FOUND_BJ_ID)
                }
                bjIdCheckState.postValue(true)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    viewEvent(NOT_FOUND_BJ_ID)
                }
                bjIdCheckState.postValue(false)
            }
        }
    }

    fun onClickCheck() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(TAAG, "${bjId.value} - onClickCheck() called")
                authUseCase.verify(VerifyRequest(bjId.value!!))
                Log.d(TAAG, "success - onClickCheck() called")
                bjIdCheckState.postValue(true)
                bjIdDetail.postValue("그런 아이디는 있네요")
            } catch (e: Exception) {
                Log.d(TAAG, "$e - onClickCheck() called")
                withContext(Dispatchers.Main) {
                    viewEvent(NOT_FOUND_BJ_ID)
                }
                bjIdCheckState.postValue(false)
            }
        }
    }

    companion object {
        const val NOT_FOUND_BJ_ID = 0
        const val FOUND_BJ_ID = 1
    }
}