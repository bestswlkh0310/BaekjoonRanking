package com.traveling.domain.model

data class Rank (
    val nickName: String?,
    val date: Int,
    val value: Int
): Comparable<Rank> {
    override fun compareTo(other: Rank): Int {
        return value.compareTo(other.value)
    }
}