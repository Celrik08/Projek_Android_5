package com.example.aplikasifilm3.APINowPlaying


data class NowPlayingResponse1(
    val dates: Dates1,
    val page: Int,
    val results: List<Movie1>,
    val total_pages: Int,
    val total_results: Int
)
