package com.bestswlkh0310.presentation.feature.onboard.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.traveling.domain.request.SigninRequest
import com.traveling.domain.usecase.AuthUseCase
import com.traveling.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
): BaseViewModel() {

    val nickName = MutableLiveData<String>("")
    val nickNameState = MutableLiveData<Boolean>(false)

    val pw = MutableLiveData<String>("")
    val pwState = MutableLiveData<Boolean>(false)

    fun onClickLogin() {
        if (nickName.value!! == "" || pw.value!! == "") {
            viewEvent(CAN_NOT_LOGIN)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    authUseCase.signinUser(SigninRequest(nickName.value!!, pw.value!!))
                    withContext(Dispatchers.Main) {
                        viewEvent(LOGIN)
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        viewEvent(CAN_NOT_LOGIN)
                    }
                }
            }
        }
    }

    companion object {
        const val LOGIN = 0
        const val CAN_NOT_LOGIN = 1
    }
}