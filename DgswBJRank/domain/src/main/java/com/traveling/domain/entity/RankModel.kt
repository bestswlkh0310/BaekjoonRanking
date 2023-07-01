package com.traveling.domain.entity

data class RankModel (
    val nickName: String?,
    val date: Int,
    val value: Int
): Comparable<RankModel> {
    override fun compareTo(other: RankModel): Int {
        return value.compareTo(other.value)
    }
}