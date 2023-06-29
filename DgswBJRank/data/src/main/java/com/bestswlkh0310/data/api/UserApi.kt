package com.bestswlkh0310.data.api

import com.traveling.domain.response.GrassesResponse
import com.traveling.domain.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("/api/v1/grass/today")
    suspend fun getToday(
        @Query("bjId") bjId: String
    ): List<UserResponse>

    @GET("/api/v1/grass/grasses")
    suspend fun getGrasses(
        @Query("bjId") bjId: String
    ): GrassesResponse

    @GET("/api/v1/grass/all-grasses")
    suspend fun getAllGrasses(): List<GrassesResponse>
}