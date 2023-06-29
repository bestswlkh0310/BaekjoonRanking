package com.traveling.domain.repository

import com.traveling.domain.model.Grasses
import com.traveling.domain.model.User

interface UserRepository {
    suspend fun getToday(bjId: String): List<User>
    suspend fun getGrasses(bjId: String): Grasses
    suspend fun getAllGrasses(): List<Grasses>
}