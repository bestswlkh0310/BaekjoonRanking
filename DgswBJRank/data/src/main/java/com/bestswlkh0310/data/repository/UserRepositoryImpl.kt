package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.remote.CRankApiClient
import com.bestswlkh0310.domain.entity.UserModel
import com.bestswlkh0310.domain.repository.UserRepository
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiClient: CRankApiClient): UserRepository {
    override fun updateAlarmToken(body: Any?) = apiClient.updateAlarmToken(body)
    override fun getUserById(id: Int) = apiClient.getUserById(id)
    override fun checkDuplicateBjId(bjId: String, id: Int) = apiClient.checkDuplicateBjId(bjId, id)
    override fun saveUserDetail(body: Any?) = apiClient.saveUserDetail(body)
}