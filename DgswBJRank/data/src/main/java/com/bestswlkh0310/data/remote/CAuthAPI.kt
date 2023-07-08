package com.bestswlkh0310.data.remote

import com.bestswlkh0310.data.Constants.BASE_ROUTER
import com.bestswlkh0310.domain.entity.AuthModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import kotlin.math.sign

interface CAuthAPI {

    @POST("${BASE_ROUTER}/sign/up")
    fun signUpUser(@Body body: Any?): Single<Response<Unit>>

    @POST("${BASE_ROUTER}/sign/in")
    fun signInUser(@Body body: Any?): Single<Response<AuthModel>>

    @POST("${BASE_ROUTER}/sign/check-duplicate-bjId/{bjId}")
    fun checkDuplicateBjId(@Path("bjId") bjId: String): Single<Response<Unit>>

    @POST("${BASE_ROUTER}/sign/check-duplicate-userId/{userId}")
    fun checkDuplicateNickName(@Path("userId") userId: String): Single<Response<Unit>>

    @POST("${BASE_ROUTER}/sign/access-token")
    fun getAccessToken(@Body body: Any?): Single<Response<AuthModel>>
}