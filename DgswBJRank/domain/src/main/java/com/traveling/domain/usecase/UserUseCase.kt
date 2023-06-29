package com.traveling.domain.usecase

import com.traveling.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getToday(bjId: String) = userRepository.getToday(bjId)
    suspend fun getGrasses(bjId: String) = userRepository.getGrasses(bjId)
    suspend fun getAllGrasses() = userRepository.getAllGrasses()
}