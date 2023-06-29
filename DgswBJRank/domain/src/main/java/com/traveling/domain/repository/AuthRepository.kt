package com.traveling.domain.repository

import com.traveling.domain.request.SigninRequest
import com.traveling.domain.request.SignupRequest
import com.traveling.domain.request.VerifyRequest

interface AuthRepository {
    suspend fun signupUser(signupRequest: SignupRequest)
    suspend fun signinUser(signinRequest: SigninRequest)
    suspend fun verify(verifyRequest: VerifyRequest)
}