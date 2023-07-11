package com.bestswlkh0310.presentation.feature.main.profile.edit_profile

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.domain.repository.UserRepository
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    val nickName = MutableLiveData<String>("")
    val bjId = MutableLiveData<String>("")
    val intro = MutableLiveData<String>("")
    val goal = MutableLiveData<String>("")

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickComplete() {
        add(userRepository.checkDuplicateBjId(bjId.value!!, DgswBJRankApplication.prefs.id).subscribe({ response ->
            when (response.code()) {
                200 -> saveUserDetail()
                204 -> viewEvent(ALREADY_EXIST_BJ_ID)
                404 -> viewEvent(NOT_VALID_BJ_ID)
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    private fun saveUserDetail() {
        add(userRepository.saveUserDetail(mapOf(
            "id" to DgswBJRankApplication.prefs.id,
            "bjId" to bjId.value,
            "intro" to intro.value,
            "goal" to goal.value
        )).subscribe({ response ->
            when (response.code()) {
                200 -> viewEvent(COMPLETE)
                else -> viewEvent(NETWORK_ERROR)
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    fun onClickCheckBjId() {
        add(userRepository.checkDuplicateBjId(bjId.value!!, DgswBJRankApplication.prefs.id).subscribe({ response ->
            when (response.code()) {
                200 -> viewEvent(VALID_BJ_ID)
                204 -> viewEvent(ALREADY_EXIST_BJ_ID)
                404 -> viewEvent(NOT_VALID_BJ_ID)
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    fun initProfile() {
        add(userRepository.getUserById(DgswBJRankApplication.prefs.id).subscribe({ response ->
            when (response.code()) {
                200 -> {
                    val body = response.body()
                    nickName.value = body!!.nickName
                    bjId.value = body.bjId?: ""
                    intro.value = body.intro?: ""
                    goal.value = body.goal?: ""
                }
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))

    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val COMPLETE = 1
        const val NOT_VALID_BJ_ID = 2
        const val VALID_BJ_ID = 3
        const val ALREADY_EXIST_BJ_ID = 4
    }
}