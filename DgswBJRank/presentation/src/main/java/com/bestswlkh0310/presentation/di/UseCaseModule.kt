package com.bestswlkh0310.presentation.di

import com.traveling.domain.repository.AuthRepository
import com.traveling.domain.repository.UserRepository
import com.traveling.domain.usecase.AuthUseCase
import com.traveling.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUserUseCase(userRepository: UserRepository) =
        UserUseCase(userRepository)

    @Singleton
    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository) =
        AuthUseCase(authRepository)
}