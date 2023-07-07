package com.bestswlkh0310.domain.entity

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("id") val id: Long,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("bjId") val pw: String,
    @SerializedName("intro") val intro: String,
    @SerializedName("goal") val goal: String,
)