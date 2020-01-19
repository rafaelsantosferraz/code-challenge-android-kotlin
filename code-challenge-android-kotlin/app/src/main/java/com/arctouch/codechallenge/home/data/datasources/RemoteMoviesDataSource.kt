package com.arctouch.codechallenge.home.data.datasources

import com.arctouch.codechallenge.home.domain.entities.Genre
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.presentation.network.TmdbApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RemoteMoviesDataSource @Inject constructor(
) {

    private val api: TmdbApi = Retrofit.Builder()
            .baseUrl(TmdbApi.URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(TmdbApi::class.java)

    suspend fun getUpcomingMovies(genres: List<Genre>): List<Movie> {
        val response = api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1, TmdbApi.DEFAULT_REGION).await()
        return response.results.map { movie ->
            movie.copy(genres = genres.filter { movie.genreIds?.contains(it.id) == true })
        }
    }

    suspend fun getGenres(): List<Genre> {
        val response = api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE).await()
        return response.genres
    }
}