package com.bestswlkh0310.domain.repository

import com.bestswlkh0310.domain.entity.TodayModel
import io.reactivex.Single
import retrofit2.Response

interface PointRepository {
    fun getPointById(id: Int): Single<Response<TodayModel>>
}