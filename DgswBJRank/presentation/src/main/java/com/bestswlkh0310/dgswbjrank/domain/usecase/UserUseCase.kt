package com.bestswlkh0310.dgswbjrank.domain.usecase

import com.bestswlkh0310.dgswbjrank.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun getUser(handle: String) = userRepository.getUser(handle)
}