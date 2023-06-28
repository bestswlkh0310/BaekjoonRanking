package com.bestswlkh0310.data.api

import com.traveling.domain.request.SigninRequest
import com.traveling.domain.request.SignupRequest
import com.traveling.domain.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("/sign/up")
    suspend fun signupUser(
        @Body signupRequest: SignupRequest
    )

    @POST("/sign/in")
    suspend fun signinUser(
        @Body signinRequest: SigninRequest
    )
}