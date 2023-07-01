package com.traveling.domain.repository

import com.traveling.domain.entity.GrassesModel
import com.traveling.domain.entity.UserModel
import io.reactivex.Single
import retrofit2.Response

interface UserRepository {
    fun getToday(bjId: String): Single<Response<List<UserModel>>>
    fun getGrasses(bjId: String): Single<Response<GrassesModel>>
    fun getAllGrasses(): Single<Response<List<GrassesModel>>>
}