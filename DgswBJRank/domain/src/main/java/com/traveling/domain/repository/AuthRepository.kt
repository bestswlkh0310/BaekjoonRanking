package com.traveling.domain.repository

import com.traveling.domain.request.SigninRequest
import com.traveling.domain.request.SignupRequest

interface AuthRepository {
    suspend fun signupUser(signupRequest: SignupRequest)
    suspend fun signinUser(signinRequest: SigninRequest)
}