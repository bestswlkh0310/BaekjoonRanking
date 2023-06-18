package com.example.baekjoonRanking.data.repository

import com.example.baekjoonRanking.domain.model.User
import com.example.baekjoonRanking.data.response.toEntity
import com.example.baekjoonRanking.data.service.UserService
import com.example.baekjoonRanking.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
): UserRepository {
    override suspend fun getUser(userId: String): User = userService.getUser(userId).toEntity()
}