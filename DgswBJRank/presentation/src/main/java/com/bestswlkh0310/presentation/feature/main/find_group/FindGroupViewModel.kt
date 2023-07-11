package com.bestswlkh0310.presentation.feature.main.find_group

import com.bestswlkh0310.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindGroupViewModel @Inject constructor(
): BaseViewModel() {

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    companion object {
        const val ON_CLICK_BACK = 0
    }
}