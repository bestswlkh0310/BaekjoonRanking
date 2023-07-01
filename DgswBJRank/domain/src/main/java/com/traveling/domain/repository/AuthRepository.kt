package com.traveling.domain.repository

import com.traveling.domain.entity.AuthModel
import io.reactivex.Single
import retrofit2.Response

interface AuthRepository {
    fun signUpUser(body: Any?): Single<Response<Unit>>
    fun signInUser(body: Any?): Single<Response<AuthModel>>
    fun checkDuplicateBjId(bjId: String): Single<Response<Unit>>
    fun checkDuplicateNickName(nickName: String): Single<Response<Unit>>
    fun getAccessToken(body: Any?): Single<Response<AuthModel>>
}