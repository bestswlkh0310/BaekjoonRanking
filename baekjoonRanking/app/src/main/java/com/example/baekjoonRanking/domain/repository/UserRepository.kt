package com.example.baekjoonRanking.domain.repository

import com.example.baekjoonRanking.domain.model.User

interface UserRepository {
    suspend fun getUser(userId: String): User
}