package com.bestswlkh0310.data.repository

import com.bestswlkh0310.data.remote.CAuthApiClient
import com.bestswlkh0310.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val apiClient: CAuthApiClient): AuthRepository {
    override fun signUpUser(body: Any?) = apiClient.signUp(body)
    override fun signInUser(body: Any?) = apiClient.signIn(body)
    override fun checkDuplicateBjId(bjId: String) = apiClient.checkDuplicateBjId(bjId)
    override fun checkDuplicateNickName(nickName: String)  = apiClient.checkDuplicateNickName(nickName)
    override fun getAccessToken(body: Any?) = apiClient.getAccessToken(body)
}