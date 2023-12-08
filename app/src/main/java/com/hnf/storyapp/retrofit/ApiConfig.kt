package com.hnf.storyapp.retrofit

import com.hnf.storyapp.model.RegisterResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig(token: String): ApiService {

    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val authInterceptor = Interceptor { chain ->
        val req = chain.request()
        val requestHeaders = req.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(requestHeaders)
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://story-api.dicoding.dev/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    override suspend fun register(name: String, email: String, password: String): RegisterResponse {
        TODO("Not yet implemented")
    }



}