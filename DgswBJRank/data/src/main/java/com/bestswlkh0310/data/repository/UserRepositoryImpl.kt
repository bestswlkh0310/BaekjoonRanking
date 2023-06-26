package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.api.UserApi
import com.bestswlkh0310.dgswbjrank.domain.repository.UserRepository
import com.bestswlkh0310.dgswbjrank.domain.response.toEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {
    override fun getUser(handle: String) = userApi.getUser(handle).toEntity()
}