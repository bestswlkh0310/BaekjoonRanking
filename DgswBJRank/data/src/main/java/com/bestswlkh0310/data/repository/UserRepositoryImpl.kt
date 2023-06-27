package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.api.UserApi
import com.traveling.domain.repository.UserRepository
import com.traveling.domain.request.SignupRequest
import com.traveling.domain.response.toEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {
    override suspend fun getUser(
        handle: String
    ) = userApi.getUser(handle).toEntity()
}