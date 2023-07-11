package com.bestswlkh0310.data.remote

import com.bestswlkh0310.data.Constants.BASE_ROUTER
import com.bestswlkh0310.domain.entity.FriendRequestModel
import com.bestswlkh0310.domain.entity.GrassesModel
import com.bestswlkh0310.domain.entity.TodayModel
import com.bestswlkh0310.domain.entity.UserModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CRankAPI {

    /**
     * grasses
     */

    @GET("${BASE_ROUTER}/grass/today/{id}")
    fun getToday(@Path("id") id: Int): Single<Response<TodayModel>>

    @GET("${BASE_ROUTER}/grass/grasses/{id}")
    fun getGrasses(@Path("id") id: Int): Single<Response<GrassesModel>>

    @GET("${BASE_ROUTER}/grass/all-grasses")
    fun getAllGrasses(): Single<Response<List<GrassesModel>>>

    /**
     * user
     */
    @POST("${BASE_ROUTER}/user/new-alarmToken")
    fun updateAlarmToken(@Body body: Any?): Single<Response<Unit>>

    @GET("${BASE_ROUTER}/user/{id}")
    fun getUserById(@Path("id") id: Int): Single<Response<UserModel>>

    @GET("${BASE_ROUTER}/user/check-duplicate-bjId/{bjId}")
    fun checkDuplicateBjId(@Path("bjId") bjId: String, @Query("id" )id: Int): Single<Response<Unit>>

    @POST("${BASE_ROUTER}/user/save-userDetail")
    fun saveUserDetail(@Body body: Any?): Single<Response<Unit>>

    /**
     * group
     */

    /**
     * point
     */


    @GET("${BASE_ROUTER}/point/{id}")
    fun getPointById(@Path("id") id: Int): Single<Response<TodayModel>>

}
