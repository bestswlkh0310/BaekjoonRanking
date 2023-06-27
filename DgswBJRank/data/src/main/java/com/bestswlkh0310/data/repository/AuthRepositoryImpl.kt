package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.api.AuthApi
import com.bestswlkh0310.data.api.UserApi
import com.traveling.domain.repository.AuthRepository
import com.traveling.domain.repository.UserRepository
import com.traveling.domain.request.SignupRequest
import com.traveling.domain.response.toEntity
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
): AuthRepository {
    override suspend fun signupUser(
        signupRequest: SignupRequest
    ) = authApi.signupUser(signupRequest)
}