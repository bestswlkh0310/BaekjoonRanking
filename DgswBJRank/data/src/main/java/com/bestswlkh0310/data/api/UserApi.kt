package com.bestswlkh0310.data.api

import com.traveling.domain.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @GET("/rank/list")
    suspend fun getUser(
        @Query("bjId") bjId: String
    ): List<UserResponse>
}