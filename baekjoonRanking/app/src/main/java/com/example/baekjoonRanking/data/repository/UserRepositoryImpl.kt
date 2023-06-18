package com.example.baekjoonRanking.data.repository

import com.example.baekjoonRanking.domain.model.User
import com.example.baekjoonRanking.data.response.toEntity
import com.example.baekjoonRanking.data.service.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) {
    suspend fun getUser(userId: String): User = userService.getUser(userId).toEntity()
}