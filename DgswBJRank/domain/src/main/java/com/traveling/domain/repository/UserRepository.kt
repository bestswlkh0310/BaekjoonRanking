package com.traveling.domain.repository

import com.traveling.domain.model.User

interface UserRepository {
    suspend fun getUser(bjId: String): List<User>
}