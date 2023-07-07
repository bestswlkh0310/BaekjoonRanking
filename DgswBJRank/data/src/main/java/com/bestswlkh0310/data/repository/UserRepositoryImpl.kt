package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.remote.CRankApiClient
import com.bestswlkh0310.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiClient: CRankApiClient): UserRepository {
    override fun updateAlarmToken(body: Any?) = apiClient.updateAlarmToken(body)
}