package com.traveling.domain.repository

import com.traveling.domain.request.SignupRequest

interface AuthRepository {
    suspend fun signupUser(signupRequest: SignupRequest)
}