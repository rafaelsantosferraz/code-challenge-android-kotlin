package com.arctouch.codechallenge.home.presentation.ui

import android.os.Bundle
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.home.presentation.network.TmdbApi
import com.arctouch.codechallenge.base.presentation.ui.BaseActivity
import com.arctouch.codechallenge.home.data.datasources.Cache
import com.arctouch.codechallenge.home.presentation.ui.adapter.HomeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1, TmdbApi.DEFAULT_REGION)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val moviesWithGenres = it.results.map { movie ->
                    movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
                }
                recyclerView.adapter = HomeAdapter(moviesWithGenres)
                progressBar.visibility = View.GONE
            }
    }
}
