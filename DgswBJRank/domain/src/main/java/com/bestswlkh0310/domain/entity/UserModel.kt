package com.bestswlkh0310.domain.entity

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("id") val id: Long,
    @SerializedName("userId") val userId: String,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("pw") val pw: String,
    @SerializedName("bjId") val bjId: String,
    @SerializedName("intro") val intro: String,
    @SerializedName("goal") val goal: String,
)