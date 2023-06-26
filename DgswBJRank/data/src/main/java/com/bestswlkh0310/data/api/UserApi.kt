package com.bestswlkh0310.data.api

import com.bestswlkh0310.dgswbjrank.domain.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("user/show")
    fun getUser(
        @Query("handle") handle: String
    ): UserResponse
}