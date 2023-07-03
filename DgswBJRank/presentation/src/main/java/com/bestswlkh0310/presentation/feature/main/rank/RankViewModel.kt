package com.bestswlkh0310.presentation.feature.main.rank

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.domain.dto.RankModel
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
): BaseViewModel() {

    val rankList = MutableLiveData<MutableList<RankModel>>(arrayListOf())

    fun reload() {
        rankList.value!!.clear()
    }

    fun getAllToday() {
        add(userRepository.getAllGrasses().subscribe({ response ->
            when (response.code()) {
                200 -> {
                    Log.d(TAAG, "success입니당 - getAllToday() called")
                    val grasses = response.body()
                    val rankData = mutableListOf<RankModel>()
                    grasses!!.map {
                        val today = it.grasses.first()
                        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                        val currentDate = Date()
                        val realToday = dateFormat.format(currentDate)
                        Log.d(TAAG, "$realToday, ${today.date} - getAllToday() called")
                        val resultValue = if (realToday == today.date.toString()) today.value else 0
                        rankData.add(
                            RankModel(
                            it.nickName,
                            today.date,
                            resultValue
                        )
                        )
                    }
                    grasses.forEach {
                        Log.d(TAAG, "$it - getAllToday() called")
                    }
                    rankList.value!!.addAll(rankData.sortedWith(compareBy { it.value }).reversed())
                    viewEvent(ADD_ALL_RANK)
                }
                else -> Log.d(TAAG, "qwertyuio - getAllToday() called")
            }
        }) {
            viewEvent(NETWORK_ERROR)
        })
    }

    companion object {
        const val ADD_ALL_RANK = 0
    }
}