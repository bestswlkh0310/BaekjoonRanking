package com.bestswlkh0310.presentation.feature.main.create_group

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.domain.repository.GroupRepository
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateGroupViewModel @Inject constructor(
    private val groupRepository: GroupRepository
): BaseViewModel() {

    val groupName = MutableLiveData<String>("")
    val groupDescription = MutableLiveData<String>("")
    val memberLimit = MutableLiveData<Int>(10)
    val memberLimitResult = MutableLiveData<String>("")

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickCreate() {
        if (groupName.value == "") viewEvent(WRONG_GROUP_NAME)
        else {
            add(groupRepository.createGroup(mapOf(
                "leaderId" to DgswBJRankApplication.prefs.id,
                "groupName" to groupName.value!!,
                "description" to groupDescription.value!!,
                "memberLimit" to memberLimit.value!!
            )).subscribe({ response ->
                when (response.code()) {
                    200 -> viewEvent(CREATE)
                    204 -> viewEvent(NEED_MORE_POINT)
                    404 -> viewEvent(ALREADY_HAVE_GROUP)
                    405 -> viewEvent(ALREADY_EXIST_GROUP_NAME)
                }
            }, {
                viewEvent(NETWORK_ERROR)
            }))
        }
    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val CREATE = 1
        const val ALREADY_EXIST_GROUP_NAME = 2
        const val NEED_MORE_POINT = 3
        const val WRONG_GROUP_NAME = 4
        const val ALREADY_HAVE_GROUP = 5
    }
}