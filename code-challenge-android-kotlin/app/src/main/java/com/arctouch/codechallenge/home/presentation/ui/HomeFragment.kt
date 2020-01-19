package com.arctouch.codechallenge.home.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.di.Injectable
import com.arctouch.codechallenge.base.presentation.ui.BaseViewModelFragment
import com.arctouch.codechallenge.home.data.datasources.Cache
import com.arctouch.codechallenge.home.presentation.network.TmdbApi
import com.arctouch.codechallenge.home.presentation.ui.adapter.HomeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.reflect.KClass


class HomeFragment : BaseViewModelFragment<HomeViewModel>(), Injectable {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val api: TmdbApi = Retrofit.Builder()
                .baseUrl(TmdbApi.URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TmdbApi::class.java)

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

    // BaseViewModelFragment -----------------------------------------------------------------------
    override fun getViewModel(): KClass<HomeViewModel> = HomeViewModel::class
}
