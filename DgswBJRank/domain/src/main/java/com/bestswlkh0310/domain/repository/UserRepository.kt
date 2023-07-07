package com.bestswlkh0310.domain.repository

import io.reactivex.Single
import retrofit2.Response

interface UserRepository {
    fun updateAlarmToken(body: Any?): Single<Response<Unit>>
}