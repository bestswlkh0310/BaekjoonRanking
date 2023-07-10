package com.bestswlkh0310.presentation.feature.main.profile.edit_profile

import com.bestswlkh0310.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(): BaseViewModel() {

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickComplete() {
        viewEvent(ON_CLICK_COMPLETE)
    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val ON_CLICK_COMPLETE = 1
    }
}