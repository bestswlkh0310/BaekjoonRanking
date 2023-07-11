package com.bestswlkh0310.domain.repository

import com.bestswlkh0310.domain.entity.GrassesModel
import com.bestswlkh0310.domain.entity.TodayModel
import io.reactivex.Single
import retrofit2.Response

interface GrassesRepository {
    fun getToday(id: Int): Single<Response<TodayModel>>
    fun getGrasses(id: Int): Single<Response<GrassesModel>>
    fun getAllGrasses(): Single<Response<List<GrassesModel>>>
}