package com.example.aplikasifilm3.APIPopular

data class PopularResponse2(
    val page: Int,
    val results: List<Movie2>,
    val total_pages: Int,
    val total_results:Int
)
