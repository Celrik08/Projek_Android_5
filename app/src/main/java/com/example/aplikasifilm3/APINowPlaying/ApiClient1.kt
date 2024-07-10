package com.example.aplikasifilm3.APINowPlaying

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient1 {

    // Ganti BASE_URL dengan URL API yang sesuai
    val BASE_URL = "https://api.themoviedb.org/3/"

    val apiService: ApiService1
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                // Tambahkan interceptor untuk logging jika diperlukan
                .addInterceptor(interceptor)
                // Tambahkan interceptor untuk menambahkan token autentikasi ke dalam header
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOWJjY2UwYzAxZTlhOTZkOTMwZjEwYmUzYzFiNWQ0ZSIsInN1YiI6IjY1ZDMwMGJkNzYxNDIxMDE3Y2ZmYzI1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.S0GqrO_e5XXS8LkhndDz7VsPwStuCuMbL_hKigB7l_A")
                        .header("api_key", "09bcce0c01e9a96d930f10be3c1b5d4e")
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiService1::class.java)
        }
}