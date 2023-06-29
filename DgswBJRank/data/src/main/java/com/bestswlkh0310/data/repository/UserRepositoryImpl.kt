package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.api.UserApi
import com.traveling.domain.model.Grasses
import com.traveling.domain.repository.UserRepository
import com.traveling.domain.response.toEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {
    override suspend fun getToday(
        bjId: String
    ) = userApi.getToday(bjId).map { it.toEntity() }

    override suspend fun getGrasses(
        bjId: String
    ) = userApi.getGrasses(bjId).toEntity()

    override suspend fun getAllGrasses(): List<Grasses>
    = userApi.getAllGrasses().map { it.toEntity() }

}