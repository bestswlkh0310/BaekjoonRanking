package com.bestswlkh0310.dgswbjrank.di

import com.bestswlkh0310.dgswbjrank.domain.repository.UserRepository
import com.bestswlkh0310.dgswbjrank.domain.usecase.UserUseCase
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
    fun provideUserUseCase(userRepository: UserRepository) = UserUseCase(userRepository)
}