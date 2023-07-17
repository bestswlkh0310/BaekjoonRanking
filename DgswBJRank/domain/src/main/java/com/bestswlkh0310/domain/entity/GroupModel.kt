package com.bestswlkh0310.domain.entity

data class GroupModel(
    val id: Int,
    val groupName: String,
    val timeStamp: Long,
    val description: String,
    val memberLimit: Int,
    val memberCount: Int
)
