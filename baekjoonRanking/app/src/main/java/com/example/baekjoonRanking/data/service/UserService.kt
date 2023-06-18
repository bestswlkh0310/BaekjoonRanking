package com.example.baekjoonRanking.data.service

import com.example.baekjoonRanking.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("user/show")
    suspend fun getUser(
        @Query("handle") userId: String
    ): UserResponse
}