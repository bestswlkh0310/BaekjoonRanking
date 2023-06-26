package com.bestswlkh0310.dgswbjrank.presentation.feature.onboard.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.dgswbjrank.domain.usecase.UserUseCase
import com.bestswlkh0310.dgswbjrank.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupAViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): BaseViewModel() {
    val nickName = MutableLiveData<String>()
    val nickNameState = MutableLiveData<Boolean>()

    val bjId = MutableLiveData<String>()
    val bjIdState = MutableLiveData<Boolean>()

    fun checkBjId(handle: String, callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            userUseCase.getUser(handle)
            callback()
        }
    }
}