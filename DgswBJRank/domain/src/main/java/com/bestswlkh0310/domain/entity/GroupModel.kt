package com.bestswlkh0310.domain.entity

data class GroupModel(
    val leaderId: Int,
    val groupName: String,
    val description: String,
    val timeStamp: Long,
    val memberLimit: Int,
    val memberCount: Int
)
