package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.remote.CRankApiClient
import com.bestswlkh0310.domain.repository.PointRepository
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(
    private val apiClient: CRankApiClient
): PointRepository {
    override fun getPointById(id: Int) = apiClient.getPointById(id)
}