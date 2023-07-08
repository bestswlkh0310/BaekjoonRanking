package com.bestswlkh0310.domain.entity

import com.google.gson.annotations.SerializedName

data class FriendRequestModel(
    @SerializedName("sender_id") val senderId: String? = null,
    @SerializedName("receiver_id") val receiverId: String? = null,
    @SerializedName("request_id") val requestId: String? = null
)
