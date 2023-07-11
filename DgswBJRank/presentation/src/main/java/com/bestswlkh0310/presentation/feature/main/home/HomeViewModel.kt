package com.bestswlkh0310.presentation.feature.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.domain.repository.GrassesRepository
import com.bestswlkh0310.domain.repository.PointRepository
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pointRepository: PointRepository,
    private val gressesRepository: GrassesRepository
): BaseViewModel() {

    val point = MutableLiveData<Int>(0)
    val solve = MutableLiveData<Int>(0)

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

    fun initSolve() {
        val id = DgswBJRankApplication.prefs.id
        add(gressesRepository.getToday(id).subscribe({ response ->
            when (response.code()) {
                200 -> solve.value = response.body()!!.value
                404 -> viewEvent(NOT_FOUND_BJ_ID)
            }
        }, {
            viewEvent(NETWORK_ERROR)
        }))
    }

    companion object {
        const val NOT_FOUND_BJ_ID = 0
    }
}