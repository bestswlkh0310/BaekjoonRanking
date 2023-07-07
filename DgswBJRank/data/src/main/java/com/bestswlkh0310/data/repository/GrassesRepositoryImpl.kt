package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.remote.CRankApiClient
import com.bestswlkh0310.domain.repository.GrassesRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiClient: CRankApiClient): GrassesRepository {
    override fun getToday(bjId: String) = apiClient.getToday(bjId)
    override fun getGrasses(bjId: String) = apiClient.getGrasses(bjId)
    override fun getAllGrasses() = apiClient.getAllGrasses()
}