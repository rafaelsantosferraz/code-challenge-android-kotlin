package com.arctouch.codechallenge.home.domain.interactors


import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.domain.repositories.MoviesRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class MoviesInteractor @Inject constructor(
        private val moviesRepository: MoviesRepository
){

    suspend fun getMoviesAsync(): Deferred<List<Movie>> = coroutineScope {
        async(Dispatchers.IO){
            moviesRepository.getUpcomingMovies()
        }
    }
}