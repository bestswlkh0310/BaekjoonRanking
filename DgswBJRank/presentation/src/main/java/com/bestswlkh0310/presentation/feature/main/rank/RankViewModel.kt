package com.bestswlkh0310.presentation.feature.main.rank

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import com.traveling.domain.entity.Rank
import com.traveling.domain.repository.AuthRepository
import com.traveling.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
): BaseViewModel() {

    val rankList = MutableLiveData<MutableList<Rank>>(arrayListOf())

    fun reload() {
        rankList.value!!.clear()
    }

    fun getAllToday() {
        add(userRepository.getAllGrasses().subscribe({ response ->
            when (response.code()) {
                200 -> {
                    val grasses = response.body()
                    val rankData = mutableListOf<Rank>()
                    grasses!!.map {
                        val today = it.grasses.first()
                        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                        val currentDate = Date()
                        val realToday = dateFormat.format(currentDate)
                        Log.d(TAAG, "$realToday, ${today.date} - getAllToday() called")
                        val resultValue = if (realToday == today.date.toString()) today.value else 0
                        rankData.add(Rank(
                            it.nickName,
                            today.date,
                            resultValue
                        ))
                    }
                    grasses.forEach {
                        Log.d(TAAG, "$it - getAllToday() called")
                    }
                    rankList.value!!.addAll(rankData.sortedWith(compareBy { it.value }).reversed())
                    viewEvent(ADD_ALL_RANK)
                }
            }
        }) {
            Log.d(TAAG, "${it.message} - getAllToday() called")
            viewEvent(NETWORK_ERROR)
        })
    }

    companion object {
        const val ADD_ALL_RANK = 0
    }
}