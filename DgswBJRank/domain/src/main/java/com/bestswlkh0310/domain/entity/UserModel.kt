package com.bestswlkh0310.domain.entity

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("id") val id: Long,
    @SerializedName("nickname") val nickName: String,
    @SerializedName("bj_id") val bjId: String,
    @SerializedName("intro") val intro: String,
    @SerializedName("goal") val goal: String,
)