package com.bestswlkh0310.presentation.feature.main.profile

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.domain.repository.PointRepository
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pointRepository: PointRepository
): BaseViewModel() {

    val point = MutableLiveData<Int>(0)

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

    companion object {
        const val ON_CLICK_EDIT_PROFILE = 0
        const val ON_CLICK_SETTING = 1
    }
}