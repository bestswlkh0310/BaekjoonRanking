package com.traveling.domain.response

import com.traveling.domain.model.Grasses

data class GrassesResponse (
    val nickName: String?,
    val bjId: String,
    val grasses: List<UserResponse>
)

fun GrassesResponse.toEntity() =
    Grasses(
        this.nickName,
        this.bjId,
        this.grasses.map { it.toEntity() }
    )
