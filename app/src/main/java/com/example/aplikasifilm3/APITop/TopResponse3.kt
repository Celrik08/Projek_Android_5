package com.example.aplikasifilm3.APITop

data class TopResponse3(
    val page: Int,
    val results: List<Movie3>,
    val total_pages: Int,
    val total_results: Int
)
