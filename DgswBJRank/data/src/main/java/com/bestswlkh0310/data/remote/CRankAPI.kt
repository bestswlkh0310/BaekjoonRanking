package com.bestswlkh0310.data.remote

import com.bestswlkh0310.domain.entity.FriendRequest
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

    @GET("/api/v1/grass/today/{bjId}")
    fun getToday(@Path("bjId") bjId: String): Single<Response<List<TodayModel>>>

    @GET("/api/v1/grass/grasses/{bjId}")
    fun getGrasses(@Path("bjId") bjId: String): Single<Response<GrassesModel>>

    @GET("/api/v1/grass/all-grasses")
    fun getAllGrasses(): Single<Response<List<GrassesModel>>>

    /**
     * friend
     */
    @POST("/api/v1/friend/request")
    fun sendFriendRequest(@Body body: Any?): Single<Response<Unit>>

    @POST("/api/v1/friend/request/accept")
    fun acceptFriendRequest(@Body body: Any?): Single<Response<Unit>>

    @PUT("/api/v1/friend/request/{requestId}")
    fun deleteFriendRequest(@Path("requestId") requestId: String): Single<Response<Unit>>

    @DELETE("/api/v1/friend/{user1Id}/{user2Id}")
    fun deleteFriend(
        @Path("user1Id") user1Id: String,
        @Path("user2Id") user2Id: String
    ): Single<Response<Unit>>

    @GET("/api/v1/friend/requests/received/{userId}")
    fun getReceivedFriendRequests(@Path("userId") userId: String): Single<Response<List<FriendRequest>>>

    @GET("/api/v1/friend/requests/sent/{userId}")
    fun getSentFriendRequests(@Path("userId") userId: String): Single<Response<List<FriendRequest>>>

    @GET("/api/v1/friend/friends/{userId}")
    fun getFriends(@Path("userId") userId: String): Single<Response<List<UserModel>>>
}