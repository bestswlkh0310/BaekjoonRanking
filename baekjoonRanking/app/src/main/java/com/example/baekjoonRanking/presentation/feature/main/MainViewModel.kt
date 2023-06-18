package com.example.baekjoonRanking.presentation.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baekjoonRanking.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun getUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getUser(userId)
            val solved = user.solvedCount
            Log.d("로그", "solved - $solved")
        }
    }
}