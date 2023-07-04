package com.bestswlkh0310.domain.dto

data class RankModel (
    val nickName: String?,
    val value: Int,
    val bjId: String
): Comparable<RankModel> {
    override fun compareTo(other: RankModel): Int {
        return value.compareTo(other.value)
    }
}