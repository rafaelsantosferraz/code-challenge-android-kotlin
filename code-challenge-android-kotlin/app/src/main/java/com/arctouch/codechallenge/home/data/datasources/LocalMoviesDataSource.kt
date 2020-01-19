package com.arctouch.codechallenge.home.data.datasources

import com.arctouch.codechallenge.home.domain.entities.Genre

object LocalMoviesDataSource {

    var genres = listOf<Genre>()

    fun cacheGenres(genres: List<Genre>) {
        LocalMoviesDataSource.genres = genres
    }
}
