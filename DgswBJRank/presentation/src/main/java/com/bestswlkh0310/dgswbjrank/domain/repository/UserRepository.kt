package com.bestswlkh0310.dgswbjrank.domain.repository

import com.bestswlkh0310.dgswbjrank.domain.model.User

interface UserRepository {
    fun getUser(handle: String): User
}