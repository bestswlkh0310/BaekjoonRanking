package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.remote.ApiClient
import com.traveling.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiClient: ApiClient): UserRepository {
    override fun getToday(bjId: String) = apiClient.getToday(bjId)
    override fun getGrasses(bjId: String) = apiClient.getGrasses(bjId)
    override fun getAllGrasses() = apiClient.getAllGrasses()
}