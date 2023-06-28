package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.api.AuthApi
import com.traveling.domain.repository.AuthRepository
import com.traveling.domain.request.SigninRequest
import com.traveling.domain.request.SignupRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
): AuthRepository {
    override suspend fun signupUser(
        signupRequest: SignupRequest
    ) = authApi.signupUser(signupRequest)

    override suspend fun signinUser(
        signinRequest: SigninRequest
    ) = authApi.signinUser(signinRequest)
}