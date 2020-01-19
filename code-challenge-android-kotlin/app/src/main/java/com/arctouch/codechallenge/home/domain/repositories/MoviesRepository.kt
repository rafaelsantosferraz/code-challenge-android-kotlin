package com.arctouch.codechallenge.home.domain.repositories

import com.arctouch.codechallenge.home.domain.entities.Genre
import com.arctouch.codechallenge.home.domain.entities.Movie

interface MoviesRepository  {

    suspend fun getUpcomingMovies(genres: List<Genre>, page: Long) : List<Movie>

    suspend fun getGenres() : List<Genre>

    suspend fun cacheGenres(genres: List<Genre>)
}