package com.bestswlkh0310.presentation.feature.main.profile

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.domain.repository.PointRepository
import com.bestswlkh0310.domain.repository.UserRepository
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pointRepository: PointRepository,
    private val userRepository: UserRepository
): BaseViewModel() {

    val point = MutableLiveData<Int>(0)
    val bjId = MutableLiveData<String>("")
    val nickName = MutableLiveData<String>("")

    fun onClickEditProfile() {
        viewEvent(ON_CLICK_EDIT_PROFILE)
    }

    fun onClickSetting() {
        viewEvent(ON_CLICK_SETTING)
    }

    fun initPoint() {
        val id = DgswBJRankApplication.prefs.id
        add(pointRepository.getPointById(id).subscribe({response ->
            when (response.code()) {
                200 -> point.value = response.body()!!.value
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    fun initProfile() {
        add(userRepository.getUserById(DgswBJRankApplication.prefs.id).subscribe({ response ->
            when (response.code()) {
                200 -> {
                    val body = response.body()!!
                    bjId.value = body.bjId?:"백준 아이디가 등록되지 않았어요!"
                    nickName.value = body.nickName
                }
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))

    }

    companion object {
        const val ON_CLICK_EDIT_PROFILE = 0
        const val ON_CLICK_SETTING = 1
    }
}