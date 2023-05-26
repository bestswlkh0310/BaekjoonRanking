package com.example.baekjoonRanking.model

data class User(
    var name: String,
    var tier: Int,
    var solvedCount: Int
) {
    constructor() : this("", -1, -1)
}