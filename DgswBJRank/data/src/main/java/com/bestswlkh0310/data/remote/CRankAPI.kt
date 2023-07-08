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

interface CRankAPI {

    /**
     * grasses
     */

    @GET("${BASE_ROUTER}/grass/today/{bjId}")
    fun getToday(@Path("bjId") bjId: String): Single<Response<List<TodayModel>>>

    @GET("${BASE_ROUTER}/grass/grasses/{bjId}")
    fun getGrasses(@Path("bjId") bjId: String): Single<Response<GrassesModel>>

    @GET("${BASE_ROUTER}/grass/all-grasses")
    fun getAllGrasses(): Single<Response<List<GrassesModel>>>

    /**
     * user
     */
    @POST("${BASE_ROUTER}/user/new-alarmToken")
    fun updateAlarmToken(@Body body: Any?): Single<Response<Unit>>


    /**
     * friend
     */
    @POST("${BASE_ROUTER}/friend/request")
    fun sendFriendRequest(@Body body: Any?): Single<Response<Unit>>

    @POST("${BASE_ROUTER}/friend/request/accept")
    fun acceptFriendRequest(@Body body: Any?): Single<Response<Unit>>

    @PUT("${BASE_ROUTER}/friend/request/{requestId}")
    fun deleteFriendRequest(@Path("requestId") requestId: String): Single<Response<Unit>>

    @DELETE("${BASE_ROUTER}/friend/{user1Id}/{user2Id}")
    fun deleteFriend(
        @Path("user1Id") user1Id: String,
        @Path("user2Id") user2Id: String
    ): Single<Response<Unit>>

    @GET("${BASE_ROUTER}/friend/requests/received/{userId}")
    fun getReceivedFriendRequests(@Path("userId") userId: String): Single<Response<List<FriendRequestModel>>>

    @GET("${BASE_ROUTER}/friend/requests/sent/{userId}")
    fun getSentFriendRequests(@Path("userId") userId: String): Single<Response<List<FriendRequestModel>>>

    @GET("${BASE_ROUTER}/friend/friends/{userId}")
    fun getFriends(@Path("userId") userId: String): Single<Response<List<UserModel>>>
}
