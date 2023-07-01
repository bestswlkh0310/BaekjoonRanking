package com.bestswlkh0310.data.remote

import com.traveling.domain.entity.AuthModel
import com.traveling.domain.entity.GrassesModel
import com.traveling.domain.entity.TodayModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ApiClient constructor(val api: API) {

    fun signIn(body: Any?): Single<Response<AuthModel>> =
        api.signInUser(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun signUp(body: Any?): Single<Response<Unit>> =
        api.signUpUser(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun checkDuplicateBjId(bjId: String): Single<Response<Unit>> =
        api.checkDuplicateBjId(bjId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun checkDuplicateNickName(nickName: String): Single<Response<Unit>> =
        api.checkDuplicateNickName(nickName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    fun getAccessToken(body: Any?): Single<Response<AuthModel>> =
        api.getAccessToken(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

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

