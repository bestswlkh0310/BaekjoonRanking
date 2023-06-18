package com.example.baekjoonRanking.data.response

import com.example.baekjoonRanking.domain.model.User

data class UserResponse(
    val backgroundId: String,
    val badgeId: Any,
    val bannedUntil: String,
    val bio: String,
    val `class`: Int,
    val classDecoration: String,
    val coins: Int,
    val exp: Int,
    val handle: String,
    val isReverseRival: Boolean,
    val isRival: Boolean,
    val joinedAt: String,
    val maxStreak: Int,
    val proUntil: String,
    val profileImageUrl: Any,
    val rank: Int,
    val rating: Int,
    val ratingByClass: Int,
    val ratingByProblemsSum: Int,
    val ratingBySolvedCount: Int,
    val ratingByVoteCount: Int,
    val reverseRivalCount: Int,
    val rivalCount: Int,
    val solvedCount: Int,
    val stardusts: Int,
    val tier: Int,
    val voteCount: Int
)

fun UserResponse.toEntity() = User(
    this.handle,
    this.tier,
    this.solvedCount
)