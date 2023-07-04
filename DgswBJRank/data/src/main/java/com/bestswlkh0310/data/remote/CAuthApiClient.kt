package com.bestswlkh0310.data.remote

import com.bestswlkh0310.domain.entity.AuthModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class CAuthApiClient constructor(val api: CAuthAPI) {
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
}