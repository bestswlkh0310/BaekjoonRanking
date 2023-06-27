package com.bestswlkh0310.data.api

import com.traveling.domain.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("user/show")
    suspend fun getUser(
        @Query("handle") handle: String
    ): UserResponse
}