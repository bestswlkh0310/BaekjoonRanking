package com.bestswlkh0310.dgswbjrank.domain.response

import com.bestswlkh0310.dgswbjrank.domain.model.User

data class UserResponse(
    val backgroundId: String,
    val badgeId: Any,
    val bannedUntil: String,
    val bio: String,
    val `class`: Int,
    val classDecoration: String,
    val coins: Int,
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

fun UserResponse.toEntity() =
    User(backgroundId, badgeId, bannedUntil, bio, `class`, classDecoration, coins, handle, isReverseRival, isRival, joinedAt, maxStreak, proUntil, profileImageUrl, rank, rating, ratingByClass, ratingByProblemsSum, ratingBySolvedCount, ratingByVoteCount, reverseRivalCount, rivalCount, solvedCount, stardusts, tier, voteCount)