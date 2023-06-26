package com.bestswlkh0310.dgswbjrank.di

import com.bestswlkh0310.data.api.UserApi
import com.bestswlkh0310.data.repository.UserRepositoryImpl
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
    fun provideUserRepository(userApi: com.bestswlkh0310.data.api.UserApi) =
        com.bestswlkh0310.data.repository.UserRepositoryImpl(userApi)
}