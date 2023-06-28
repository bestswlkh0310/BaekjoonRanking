package com.bestswlkh0310.presentation.feature.onboard.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.traveling.domain.request.SignupRequest
import com.traveling.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupBViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): BaseViewModel() {
    val intro = MutableLiveData<String>("")
    val goal = MutableLiveData<String>("")

    val nickname = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val bjId = MutableLiveData<String>()

    fun onClickSignup() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                authUseCase.signupUser(
                    SignupRequest(
                        nickname = nickname.value!!,
                        pw = pw.value!!,
                        bjId = bjId.value!!,
                        intro = intro.value!!,
                        goal = goal.value!!
                    )
                )
                withContext(Dispatchers.Main) {
                    viewEvent(SIGN_UP)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    viewEvent(CAN_NOT_SIGN_UP)
                }
            }
        }
    }
    companion object {
        const val SIGN_UP = 0
        const val CAN_NOT_SIGN_UP = 1
    }
}