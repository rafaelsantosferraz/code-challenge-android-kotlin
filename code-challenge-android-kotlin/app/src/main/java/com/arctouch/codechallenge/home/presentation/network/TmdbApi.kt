package com.arctouch.codechallenge.home.presentation.network

import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.presentation.network.models.GenreResponse
import com.arctouch.codechallenge.home.presentation.network.models.UpcomingMoviesResponse
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    companion object {
        const val URL = BuildConfig.URL
        const val API_KEY = BuildConfig.API_KEY
        const val DEFAULT_LANGUAGE = "pt-BR"
        const val DEFAULT_REGION = "BR"
    }

    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Deferred<GenreResponse>

    @GET("movie/upcoming")
    fun upcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Long,
        @Query("region") region: String
    ): Deferred<UpcomingMoviesResponse>

    @GET("movie/{id}")
    fun movie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<Movie>
}
