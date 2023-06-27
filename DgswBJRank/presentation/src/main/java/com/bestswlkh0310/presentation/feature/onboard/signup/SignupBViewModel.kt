package com.bestswlkh0310.presentation.feature.onboard.signup

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupBViewModel @Inject constructor(

): BaseViewModel() {
    val intro = MutableLiveData<String>()
    val goal = MutableLiveData<String>()

    fun onClickSignup() {
        viewEvent(SIGN_UP)
    }
    companion object {
        const val SIGN_UP = 0
    }
}