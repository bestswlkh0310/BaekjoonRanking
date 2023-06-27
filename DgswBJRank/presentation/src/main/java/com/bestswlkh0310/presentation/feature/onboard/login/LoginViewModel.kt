package com.bestswlkh0310.presentation.feature.onboard.login

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): BaseViewModel() {

    val nickName = MutableLiveData<String>()
    val nickNameState = MutableLiveData<Boolean>()

    fun onClickLogin() {
        viewEvent(LOGIN)
    }

    companion object {
        const val LOGIN = 0
    }
}