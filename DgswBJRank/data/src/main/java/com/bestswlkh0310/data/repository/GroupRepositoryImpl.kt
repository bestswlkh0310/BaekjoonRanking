package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.remote.CRankApiClient
import com.bestswlkh0310.domain.repository.GroupRepository
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val apiClient: CRankApiClient
): GroupRepository {
    override fun createGroup(body: Any?) = apiClient.createGroup(body)
}