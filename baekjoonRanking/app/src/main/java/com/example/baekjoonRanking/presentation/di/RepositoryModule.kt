package com.example.baekjoonRanking.presentation.di

import com.example.baekjoonRanking.data.repository.UserRepositoryImpl
import com.example.baekjoonRanking.data.service.UserService
import com.example.baekjoonRanking.domain.repository.UserRepository
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
    fun provideUserRepository(userService: UserService): UserRepository = UserRepositoryImpl(userService)
}