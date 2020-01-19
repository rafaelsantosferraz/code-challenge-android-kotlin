package com.arctouch.codechallenge.home.data.datasources

import com.arctouch.codechallenge.home.domain.entities.Genre
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.presentation.network.TmdbApi
import javax.inject.Inject

class RemoteMoviesDataSource @Inject constructor(
        private val api: TmdbApi
) {

    suspend fun getUpcomingMovies(genres: List<Genre>, page: Long): List<Movie> {
        val response = api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page, TmdbApi.DEFAULT_REGION).await()
        return response.results.map { movie ->
            movie.copy(genres = genres.filter { movie.genreIds?.contains(it.id) == true })
        }
    }

    suspend fun getGenres(): List<Genre> {
        val response = api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE).await()
        return response.genres
    }
}