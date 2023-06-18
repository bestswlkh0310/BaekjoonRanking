package com.example.baekjoonRanking.network

import com.example.baekjoonRanking.network.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("user/show")
    fun getUser(@Query("handle") userId: String): Call<UserResponse>
}