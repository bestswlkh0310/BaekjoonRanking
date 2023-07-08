package com.bestswlkh0310.domain.entity

import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("id") val id: Int
)