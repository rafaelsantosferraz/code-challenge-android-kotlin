package com.arctouch.codechallenge.home.domain.repositories

import com.arctouch.codechallenge.home.domain.entities.Movie

interface MoviesRepository  {

    suspend fun getUpcomingMovies() : List<Movie>
}