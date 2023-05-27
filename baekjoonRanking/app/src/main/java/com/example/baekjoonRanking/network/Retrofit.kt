package com.example.baekjoonRanking.network

import com.example.baekjoonRanking.model.User
import com.example.baekjoonRanking.network.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object Retrofit {
    suspend fun getUser(userId: String): User {
        return suspendCoroutine { continuation ->
            val call = RetrofitClient.API.getUser(userId)
            call.enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        val result = response.body()!!
                        val user = User(
                            name = result.handle,
                            tier = result.tier,
                            solvedCount = result.solvedCount
                        )
                        continuation.resume(user)
                    } else {
                        continuation.resume(User())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    continuation.resume(User())
                }
            })
        }
    }
}