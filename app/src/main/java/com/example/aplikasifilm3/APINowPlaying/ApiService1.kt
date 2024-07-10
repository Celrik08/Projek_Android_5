package com.example.aplikasifilm3.APINowPlaying

import retrofit2.Call
import retrofit2.http.GET

interface ApiService1 {

    @GET("movie/now_playing") // Menggunakan endpoint yang sesuai
    fun getNowPlayingMovies(): Call<NowPlayingResponse1> // Mengubah tipe data response menjadi NowPlayingResponse
}