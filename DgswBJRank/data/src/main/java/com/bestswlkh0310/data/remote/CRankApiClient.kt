package com.bestswlkh0310.data.remote

import com.bestswlkh0310.domain.entity.AuthModel
import com.bestswlkh0310.domain.entity.GrassesModel
import com.bestswlkh0310.domain.entity.TodayModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class CRankApiClient constructor(val api: CRankAPI) {

    fun getToday(bjId: String): Single<Response<List<TodayModel>>> =
        api.getToday(bjId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getGrasses(bjId: String): Single<Response<GrassesModel>> =
        api.getGrasses(bjId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getAllGrasses(): Single<Response<List<GrassesModel>>> =
        api.getAllGrasses()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

