package com.traveling.domain.request

data class SignupRequest (
    val nickname: String,
    val pw: String,
    val bjId: String,
    val intro: String,
    val goal: String
)