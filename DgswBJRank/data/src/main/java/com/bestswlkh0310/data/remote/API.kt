package com.bestswlkh0310.data.remote

import com.traveling.domain.entity.AuthModel
import com.traveling.domain.entity.GrassesModel
import com.traveling.domain.entity.TodayModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    /**
     * auth
     */

    @POST("/api/v1/sign/up")
    fun signUpUser(@Body body: Any?): Single<Response<Unit>>

    @POST("/api/v1/sign/in")
    fun signInUser(@Body body: Any?): Single<Response<AuthModel>>

    @POST("/api/v1/sign/check-duplicate-bjId")
    fun checkDuplicateBjId(@Query("bjId") bjId: String): Single<Response<Unit>>

    @POST("/api/v1/sign/check-duplicate-nickName")
    fun checkDuplicateNickName(@Query("nickName") nickName: String): Single<Response<Unit>>

    @POST("/api/v1/sign/access-token")
    fun getAccessToken(@Body body: Any?): Single<Response<AuthModel>>

    /**
     * grasses
     */

    @GET("/api/v1/grass/today")
    fun getToday(@Query("bjId") bjId: String): Single<Response<List<TodayModel>>>

    @GET("/api/v1/grass/grasses")
    fun getGrasses(@Query("bjId") bjId: String): Single<Response<GrassesModel>>

    @GET("/api/v1/grass/all-grasses")
    fun getAllGrasses(): Single<Response<List<GrassesModel>>>
}
