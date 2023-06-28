package com.bestswlkh0310.presentation.di

import com.bestswlkh0310.data.api.AuthApi
import com.bestswlkh0310.data.api.UserApi
import com.bestswlkh0310.data.repository.AuthRepositoryImpl
import com.bestswlkh0310.data.repository.UserRepositoryImpl
import com.traveling.domain.repository.AuthRepository
import com.traveling.domain.repository.UserRepository
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
    fun provideUserRepository(@MyRetrofit userApi: UserApi): UserRepository =
        UserRepositoryImpl(userApi)

    @Singleton
    @Provides
    fun provideAuthRepository(@MyRetrofit authApi: AuthApi): AuthRepository =
        AuthRepositoryImpl(authApi)
}