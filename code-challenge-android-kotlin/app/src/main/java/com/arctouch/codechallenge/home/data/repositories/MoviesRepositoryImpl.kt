package com.arctouch.codechallenge.home.data.repositories

import androidx.paging.PagedList
import com.arctouch.codechallenge.home.data.datasources.LocalMoviesDataSource
import com.arctouch.codechallenge.home.data.datasources.RemoteMoviesDataSource
import com.arctouch.codechallenge.home.domain.entities.Genre
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.domain.repositories.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepositoryImpl @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource,
        private val localMoviesDataSource: LocalMoviesDataSource
) : MoviesRepository{

    override suspend fun getUpcomingMovies(genres: List<Genre>, page: Long) : List<Movie> =
        remoteMoviesDataSource.getUpcomingMovies(genres, page)


    override suspend fun getGenres() : List<Genre> =
        if(LocalMoviesDataSource.genres.isEmpty()){
            val genres = remoteMoviesDataSource.getGenres()
            cacheGenres(genres)
            genres
        } else {
            LocalMoviesDataSource.genres
        }

    override suspend fun cacheGenres(genres: List<Genre>) {
        localMoviesDataSource.cacheGenres(genres)
    }

}