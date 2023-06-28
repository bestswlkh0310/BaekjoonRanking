package com.traveling.domain.usecase

import com.traveling.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getUser(bjId: String) = userRepository.getUser(bjId)
}