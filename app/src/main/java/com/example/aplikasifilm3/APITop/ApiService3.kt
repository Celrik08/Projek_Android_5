package com.example.aplikasifilm3.APITop

import retrofit2.Call
import retrofit2.http.GET

interface ApiService3 {

    @GET("movie/top_rated") // Menggunakan endpoint yang sesuai
    fun getTopMovies(): Call<TopResponse3> // Mengubah tipe data response menjadi NowPlayingResponse
}