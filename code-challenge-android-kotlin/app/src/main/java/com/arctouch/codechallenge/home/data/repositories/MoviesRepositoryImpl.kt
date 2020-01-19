package com.arctouch.codechallenge.home.data.repositories

import com.arctouch.codechallenge.home.data.datasources.RemoteMoviesDataSource
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.domain.repositories.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepositoryImpl @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource
) : MoviesRepository{

    override suspend fun getUpcomingMovies() : List<Movie> {
        return remoteMoviesDataSource.getUpcomingMovies()
    }


}