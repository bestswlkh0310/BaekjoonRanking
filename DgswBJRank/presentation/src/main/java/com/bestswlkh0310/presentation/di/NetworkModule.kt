package com.bestswlkh0310.presentation.di

import android.util.Log
import com.bestswlkh0310.data.remote.CAuthAPI
import com.bestswlkh0310.data.remote.CAuthApiClient
import com.bestswlkh0310.data.remote.CRankAPI
import com.bestswlkh0310.data.remote.CRankApiClient
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import com.google.gson.GsonBuilder
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.presentation.util.Constant.AUTH_URL
import com.bestswlkh0310.presentation.util.Constant.RANK_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CAuthOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CRankOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CAuthRemoteRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CRankRemoteRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCAuthApiClient(api: CAuthAPI) = CAuthApiClient(api)

    @Singleton
    @Provides
    fun provideCRankApiClient(api: CRankAPI) = CRankApiClient(api)

    @Singleton
    @Provides
    fun provideCAuthApi(@CAuthRemoteRetrofit retrofit: Retrofit): CAuthAPI = retrofit.create(CAuthAPI::class.java)

    @Singleton
    @Provides
    fun provideCRankApi(@CRankRemoteRetrofit retrofit: Retrofit): CRankAPI = retrofit.create(CRankAPI::class.java)

    @Singleton
    @Provides
    @CAuthRemoteRetrofit
    fun provideCAuthRetrofit(@CAuthOkHttpClient okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AUTH_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @CRankRemoteRetrofit
    fun provideCRankRetrofit(@CRankOkHttpClient okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RANK_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @CAuthOkHttpClient
    fun provideCAuthOkHttpClient(
        headerInterceptor: Interceptor,
        loggerInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(loggerInterceptor)
        okHttpClientBuilder.addInterceptor(headerInterceptor)
        okHttpClientBuilder.addInterceptor(authInterceptor)

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    @CRankOkHttpClient
    fun provideCRankOkHttpClient(
        headerInterceptor: Interceptor,
        loggerInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(loggerInterceptor)
        okHttpClientBuilder.addInterceptor(headerInterceptor)
        okHttpClientBuilder.addInterceptor(authInterceptor)

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideHeaderInterceptor() = Interceptor { chain ->
        with(chain) {
            Log.d(TAAG, "${DgswBJRankApplication.prefs.accessToken} - provideHeaderInterceptor() called")
            val newRequest = request().newBuilder()
                .addHeader("authorization", "Bearer ${DgswBJRankApplication.prefs.accessToken}")
                .build()
            proceed(newRequest)
        }
    }
}

class AuthInterceptor @Inject constructor(
    private val authRepository: Provider<AuthRepository>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.encodedPath.startsWith("/api/v1/sign/")) {
            return chain.proceed(request)
        }

        val response = chain.proceed(request)
        Log.d(TAAG, "code: ${response.code}- intercept() called")

        if (response.code == 403) {
            response.close()
            Log.d(TAAG, "access 토큰 만료 - ${DgswBJRankApplication.prefs.refreshToken}() called")
            val res = authRepository.get().getAccessToken(mapOf("refreshToken" to DgswBJRankApplication.prefs.refreshToken)).blockingGet()
            when (res.code()) {
                200 -> {
                    Log.d(TAAG, "새로운 토큰 - ${res.body()!!.token}")
                    DgswBJRankApplication.prefs.accessToken = res.body()!!.token
                    val newRequest = request.newBuilder()
                        .header("Authorization", "Bearer ${res.body()!!.token}")
                        .build()
                    return chain.proceed(newRequest)
                }
            }
            
            Log.d(TAAG, "${res.code()}, ${res.body()} - intercept() called")
        } else if (response.code == 401) {
            response.close()
            Log.d(TAAG, "액세스 토큰 이상해! - intercept() called")
        }
        return response
    }
}