package com.bestswlkh0310.presentation.feature.main.rank

import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.domain.dto.RankModel
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.domain.repository.GrassesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val grassesRepository: GrassesRepository,
    private val authRepository: AuthRepository
): BaseViewModel() {

    val rankList = MutableLiveData<MutableList<RankModel>>(arrayListOf())
    val seasonTotal = MutableLiveData<Int>()

    fun reload() {
        viewEvent(REMOVE_ALL)
        rankList.value!!.clear()
    }

    fun getSeasonTotal() {
        add(grassesRepository.getAllGrasses().subscribe({ response ->
            when (response.code()) {
                200 -> {
                    val grasses = response.body()
                    if (grasses == null) {
                        viewEvent(NOT_EXIST_USER)
                    } else {
                        val result = grasses.sumOf {
                            val calendar = Calendar.getInstance()
                            with(calendar) { set(get(Calendar.YEAR), get(Calendar.MONTH), 1) }
                            val startDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(calendar.time)
                            it.grasses.filter { day -> startDate.toInt() <= day.date }.sumOf { value -> value.value }
                        }
                        seasonTotal.value = result
                    }
                }
            }
        }) {
            viewEvent(NETWORK_ERROR)
        })
    }

    fun getAllToday() {
        add(grassesRepository.getAllGrasses().subscribe({ response ->
            when (response.code()) {
                200 -> {
                    reload()
                    val grasses = response.body()
                    val rankData = mutableListOf<RankModel>()
                    if (grasses == null) {
                        viewEvent(NOT_EXIST_USER)
                    } else {
                        grasses.forEach {
                            val today = it.grasses.first()
                            val realToday = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
                            val resultValue = if (realToday == today.date.toString()) today.value else 0
                            rankData.add(RankModel(it.nickName, resultValue, it.bjId))
                        }
                        rankList.value!!.addAll(rankData.sortedWith(compareBy { it.value }).reversed())
                        viewEvent(ADD_ALL_RANK)
                    }
                }
            }
        }) {
            viewEvent(NETWORK_ERROR)
        })
    }

    fun getAllSeason() {
        add(grassesRepository.getAllGrasses().subscribe({ response ->
            when (response.code()) {
                200 -> {
                    reload()
                    val grasses = response.body()
                    val rankData = mutableListOf<RankModel>()
                    grasses!!.forEach {
                        val calendar = Calendar.getInstance()
                        with(calendar) { set(get(Calendar.YEAR), get(Calendar.MONTH), 1) }
                        val startDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(calendar.time)
                        val monthSum = it.grasses.filter { day -> startDate.toInt() <= day.date }.sumOf { value -> value.value }
                        rankData.add(RankModel(it.nickName, monthSum, it.bjId))
                    }
                    rankList.value!!.addAll(rankData.sortedWith(compareBy { it.value }).reversed())
                    viewEvent(ADD_ALL_RANK)
                }
            }
        }) {
            viewEvent(NETWORK_ERROR)
        })
    }

    companion object {
        const val ADD_ALL_RANK = 0
        const val REMOVE_ALL = 1
        const val NOT_EXIST_USER = 2
    }
}