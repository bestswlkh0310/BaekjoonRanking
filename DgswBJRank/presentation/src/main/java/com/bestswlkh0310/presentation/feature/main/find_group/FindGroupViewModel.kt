package com.bestswlkh0310.presentation.feature.main.find_group

import com.bestswlkh0310.domain.entity.GroupModel
import com.bestswlkh0310.domain.repository.GroupRepository
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindGroupViewModel @Inject constructor(
    private val groupRepository: GroupRepository
): BaseViewModel() {

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun initGroups(groupList: MutableList<GroupModel>) {
        add(groupRepository.getAllGroup().subscribe({ response ->
            when (response.code()) {
                200 -> {
                    response.body()!!.forEach { groupList.add(it) }
                    viewEvent(LOAD_GROUPS)
                }
                404 -> viewEvent(NOT_FOUND_GROUPS)
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    fun join(group: GroupModel) {
        add(groupRepository.join(mapOf(
            "id" to DgswBJRankApplication.prefs.id,
            "groupId" to group.id
        )).subscribe({ response ->
            when (response.code()) {
                200 -> viewEvent(JOIN)
                204 -> viewEvent(CAN_NOT_JOIN_GROUP)
                405 -> viewEvent(ALREADY_HAVE_GROUP)
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val LOAD_GROUPS = 1
        const val NOT_FOUND_GROUPS = 2
        const val JOIN = 3
        const val ALREADY_HAVE_GROUP = 4
        const val CAN_NOT_JOIN_GROUP = 5

    }
}