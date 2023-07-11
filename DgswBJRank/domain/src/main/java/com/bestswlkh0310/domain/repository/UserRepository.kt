package com.bestswlkh0310.domain.repository

import com.bestswlkh0310.domain.entity.UserModel
import io.reactivex.Single
import retrofit2.Response

interface UserRepository {
    fun updateAlarmToken(body: Any?): Single<Response<Unit>>
    fun getUserById(id: Int): Single<Response<UserModel>>
    fun checkDuplicateBjId(bjId: String, id: Int): Single<Response<Unit>>
    fun saveUserDetail(body: Any?): Single<Response<Unit>>
}