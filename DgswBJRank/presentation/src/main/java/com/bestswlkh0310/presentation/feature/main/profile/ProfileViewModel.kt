package com.bestswlkh0310.presentation.feature.main.profile

import com.bestswlkh0310.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): BaseViewModel() {

    fun onClickEditProfile() {
        viewEvent(ON_CLICK_EDIT_PROFILE)
    }

    fun onClickSetting() {
        viewEvent(ON_CLICK_SETTING)
    }

    companion object {
        const val ON_CLICK_EDIT_PROFILE = 0
        const val ON_CLICK_SETTING = 1
    }
}