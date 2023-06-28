package com.bestswlkh0310.presentation.di

import com.bestswlkh0310.data.api.AuthApi
import com.bestswlkh0310.data.api.UserApi
import com.bestswlkh0310.presentation.util.Constant.MY_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MyOkHttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MyRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @MyRetrofit
    fun provideAuthApi(@MyRetrofit retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Singleton
    @Provides
    @MyRetrofit
    fun provideUserApi(@MyRetrofit retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @MyOkHttpClient
    @Singleton
    @Provides
    fun provideMyOkHttpClient(
        LoggerInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(LoggerInterceptor)

        return okHttpClientBuilder.build()
    }

    @MyRetrofit
    @Singleton
    @Provides
    fun provideMyRetrofit(@MyOkHttpClient okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MY_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}