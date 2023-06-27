package com.traveling.domain.usecase

import com.traveling.domain.repository.AuthRepository
import com.traveling.domain.repository.UserRepository
import com.traveling.domain.request.SignupRequest
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun signupUser(signupRequest: SignupRequest) = authRepository.signupUser(signupRequest)
}