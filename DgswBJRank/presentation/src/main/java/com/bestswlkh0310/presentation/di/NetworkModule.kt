package com.bestswlkh0310.presentation.di

import android.content.Context
import android.util.Log
import com.bestswlkh0310.data.remote.API
import com.bestswlkh0310.data.remote.ApiClient
import com.bestswlkh0310.presentation.util.Constant.MY_URL
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import com.google.gson.GsonBuilder
import com.traveling.domain.repository.AuthRepository
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
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi(@Named("retrofit") retrofit: Retrofit): API = retrofit.create(API::class.java)

    @Singleton
    @Provides
    fun provideApiClient(api: API) = ApiClient(api)

    @Singleton
    @Provides
    fun provideMyOkHttpClient(
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
    @Named("retrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MY_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
    private val context: Context,
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
//            showToast("다시 로그인 하세용")
            Log.d(TAAG, "액세스 토큰 이상해! - intercept() called")
        }
        return response
    }

    private fun showToast(message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}