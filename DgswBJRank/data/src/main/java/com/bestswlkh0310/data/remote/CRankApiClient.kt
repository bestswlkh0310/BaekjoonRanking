package com.bestswlkh0310.data.remote

import com.bestswlkh0310.domain.entity.GrassesModel
import com.bestswlkh0310.domain.entity.GroupModel
import com.bestswlkh0310.domain.entity.TodayModel
import com.bestswlkh0310.domain.entity.UserModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class CRankApiClient constructor(val api: CRankAPI) {

    /**
     * grass
     */
    fun getToday(id: Int): Single<Response<TodayModel>> =
        api.getToday(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getGrasses(id: Int): Single<Response<GrassesModel>> =
        api.getGrasses(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getAllGrasses(): Single<Response<List<GrassesModel>>> =
        api.getAllGrasses()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /**
     * user
     */
    fun updateAlarmToken(body: Any?): Single<Response<Unit>> =
        api.updateAlarmToken(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getUserById(id: Int): Single<Response<UserModel>> =
        api.getUserById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun checkDuplicateBjId(bjId: String, id: Int) =
        api.checkDuplicateBjId(bjId, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun saveUserDetail(body: Any?)=
        api.saveUserDetail(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /**
     * point
     */
    fun getPointById(id: Int): Single<Response<TodayModel>> =
        api.getPointById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    /**
     * group
     */

    fun createGroup(body: Any?): Single<Response<Unit>> =
        api.createGroup(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getAllGroup(): Single<Response<List<GroupModel>>> =
        api.getAllGroup()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    fun join(body: Any?): Single<Response<Unit>> =
        api.join(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

