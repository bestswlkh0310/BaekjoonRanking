package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.remote.CRankApiClient
import com.bestswlkh0310.domain.repository.GrassesRepository
import javax.inject.Inject

class GrassesRepositoryImpl @Inject constructor(private val apiClient: CRankApiClient): GrassesRepository {
    override fun getToday(id: Int) = apiClient.getToday(id)
    override fun getGrasses(id: Int) = apiClient.getGrasses(id)
    override fun getAllGrasses() = apiClient.getAllGrasses()
}