package com.traveling.domain.response

import com.traveling.domain.model.User

data class UserResponse (
    val date: Int,
    val value: Int
)

fun UserResponse.toEntity() =
    User(
        this.date,
        this.value
    )