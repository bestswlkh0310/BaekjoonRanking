package com.traveling.domain.repository

import com.traveling.domain.model.User

interface UserRepository {
    suspend fun getUser(handle: String): User
}