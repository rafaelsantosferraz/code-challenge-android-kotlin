package com.arctouch.codechallenge.home.presentation.network.models

import com.arctouch.codechallenge.home.domain.entities.Movie
import com.squareup.moshi.Json


data class UpcomingMoviesResponse(
        val page: Int,
        val results: List<Movie>,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Int
)
