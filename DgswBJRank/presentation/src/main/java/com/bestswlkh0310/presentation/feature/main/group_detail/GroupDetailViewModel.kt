package com.bestswlkh0310.presentation.feature.main.group_detail

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.domain.repository.GroupRepository
import com.bestswlkh0310.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    private val groupRepository: GroupRepository
): BaseViewModel() {

    val groupName = MutableLiveData<String>("")

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    companion object {
        const val ON_CLICK_BACK = 0
    }

}