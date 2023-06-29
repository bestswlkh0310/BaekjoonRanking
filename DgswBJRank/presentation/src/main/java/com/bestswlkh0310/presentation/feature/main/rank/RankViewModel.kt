package com.bestswlkh0310.presentation.feature.main.rank

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.traveling.domain.model.Rank
import com.traveling.domain.usecase.UserUseCase
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
    private val userUseCase: UserUseCase
): BaseViewModel() {

    val rankList = MutableLiveData<MutableList<Rank>>(arrayListOf())

    fun reload() {
        rankList.value!!.clear()
    }

    fun getAllToday() {
        viewModelScope.launch(Dispatchers.IO) {
            val grasses = userUseCase.getAllGrasses()
            val rankData = mutableListOf<Rank>()
            grasses.map {
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
            withContext(Dispatchers.Main) {
                viewEvent(ADD_ALL_RANK)
            }
        }
    }

    companion object {
        const val ADD_ALL_RANK = 0
    }
}