package com.bestswlkh0310.presentation.di

import com.bestswlkh0310.data.remote.CAuthApiClient
import com.bestswlkh0310.data.remote.CRankApiClient
import com.bestswlkh0310.data.repository.AuthRepositoryImpl
import com.bestswlkh0310.data.repository.UserRepositoryImpl
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(apiClient: CRankApiClient): UserRepository =
        UserRepositoryImpl(apiClient)

    @Singleton
    @Provides
    fun provideAuthRepository(apiClient: CAuthApiClient): AuthRepository =
        AuthRepositoryImpl(apiClient)
}