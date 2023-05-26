package com.example.baekjoonRanking.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val url = "https://solved.ac/api/v3/"
    private val loggingInterceptor = HttpLoggingInterceptor {
            message ->
        Log.d("로그", "$message - RetrofitClient() called")
    }.setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)


    var server: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()
    var API: UserService = server.create(UserService::class.java)
}