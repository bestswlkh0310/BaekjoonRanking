package com.bestswlkh0310.data.remote

import com.bestswlkh0310.domain.entity.AuthModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CAuthAPI {

    @POST("/api/v1/sign/up")
    fun signUpUser(@Body body: Any?): Single<Response<Unit>>

    @POST("/api/v1/sign/in")
    fun signInUser(@Body body: Any?): Single<Response<AuthModel>>

    @POST("/api/v1/sign/check-duplicate-bjId/{bjId}")
    fun checkDuplicateBjId(@Path("bjId") bjId: String): Single<Response<Unit>>

    @POST("/api/v1/sign/check-duplicate-nickName/{nickName}")
    fun checkDuplicateNickName(@Path("nickName") nickName: String): Single<Response<Unit>>

    @POST("/api/v1/sign/access-token")
    fun getAccessToken(@Body body: Any?): Single<Response<AuthModel>>

}