package com.bestswlkh0310.presentation.feature.main.group

import com.bestswlkh0310.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(

): BaseViewModel() {
    fun onClickFindGroup() {
        viewEvent(ON_CLICK_FIND_GROUP)
    }

    fun onClickCreateGroup() {
        viewEvent(ON_CLICK_CREATE_GROUP)
    }

    companion object {
        const val ON_CLICK_FIND_GROUP = 0
        const val ON_CLICK_CREATE_GROUP = 1
    }
}