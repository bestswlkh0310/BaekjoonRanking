package com.bestswlkh0310.domain.repository

import com.bestswlkh0310.domain.entity.GrassesModel
import com.bestswlkh0310.domain.entity.TodayModel
import io.reactivex.Single
import retrofit2.Response

interface UserRepository {
    fun getToday(bjId: String): Single<Response<List<TodayModel>>>
    fun getGrasses(bjId: String): Single<Response<GrassesModel>>
    fun getAllGrasses(): Single<Response<List<GrassesModel>>>
}