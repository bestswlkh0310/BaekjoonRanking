package com.bestswlkh0310.data.api

import com.traveling.domain.request.SigninRequest
import com.traveling.domain.request.SignupRequest
import com.traveling.domain.request.VerifyRequest
import com.traveling.domain.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("/api/v1/sign/up")
    suspend fun signupUser(
        @Body signupRequest: SignupRequest
    )

    @POST("/api/v1/sign/in")
    suspend fun signinUser(
        @Body signinRequest: SigninRequest
    )

    @POST("/api/v1/sign/verify")
    suspend fun verify(
        @Body verifyRequest: VerifyRequest
    )
}