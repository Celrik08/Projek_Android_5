package com.example.aplikasifilm3.APIPopular

import retrofit2.Call
import retrofit2.http.GET

interface ApiService2 {

    @GET("movie/popular") // Menggunakan endpoint yang sesuai
    fun getPopularMovies(): Call<PopularResponse2> // Mengubah tipe data response menjadi NowPlayingResponse
}