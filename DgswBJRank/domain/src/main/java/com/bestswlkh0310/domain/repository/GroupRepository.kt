package com.bestswlkh0310.domain.repository

import com.bestswlkh0310.domain.entity.GroupModel
import io.reactivex.Single
import retrofit2.Response

interface GroupRepository {
    fun createGroup(body: Any?): Single<Response<Unit>>
    fun getAllGroup(): Single<Response<List<GroupModel>>>
    fun join(body: Any?): Single<Response<Unit>>
}
