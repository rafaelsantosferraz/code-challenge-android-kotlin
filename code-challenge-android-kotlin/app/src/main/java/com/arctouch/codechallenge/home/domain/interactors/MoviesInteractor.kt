package com.arctouch.codechallenge.home.domain.interactors


import com.arctouch.codechallenge.home.domain.entities.Genre
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

    private var page: Long = 1
    var isLastPage: Boolean = false

    //region Public
    suspend fun getMoviesAsync(previousLoadMovies: MutableList<Movie>): Deferred<List<Movie>> = coroutineScope {
        async(Dispatchers.IO){
            val genres = moviesRepository.getGenres()
            var moviesList : MutableList<Movie>

            if(previousLoadMovies.isNullOrEmpty()) {
                page = 1
                moviesList = moviesRepository.getUpcomingMovies(genres, page).toMutableList()
                page++
            }
            else {
                moviesList = previousLoadMovies
                val nextPage = moviesRepository.getUpcomingMovies(genres, page)

                if(nextPage.isNullOrEmpty()){
                    isLastPage = true
                } else {
                    moviesList.addAll(nextPage)
                    page++
                }
            }
            moviesList
        }
    }
    //endregion



    //region Private
    private suspend fun getGenresAsync(): Deferred<List<Genre>> = coroutineScope {
        async(Dispatchers.IO){
            moviesRepository.getGenres()
        }
    }
    //endregion
}