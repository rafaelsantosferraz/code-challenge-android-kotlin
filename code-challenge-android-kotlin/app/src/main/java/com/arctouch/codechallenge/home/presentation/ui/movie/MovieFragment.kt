package com.arctouch.codechallenge.home.presentation.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.presentation.ui.BaseFragment
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : BaseFragment() {






    //region Super ---------------------------------------------------------------------------------
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        setupArgs(MovieFragmentArgs.fromBundle(arguments!!))
    }
    //endregion



    //region Setup ---------------------------------------------------------------------------------
    private fun setupArgs(movieFragmentArgs: MovieFragmentArgs) {

        val movieImageUrlBuilder = MovieImageUrlBuilder()

        movieFragmentArgs.apply {
            Glide.with(this@MovieFragment)
                    .load(movie.posterPath?.let { movieImageUrlBuilder.buildBackdropUrl(it) })
                    .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(movie_fragment_banner_iv)

            movie_fragment_title_tv.text       = movie.title
            movie_fragment_releasedate_tv.text = movie.releaseDate
            movie_fragment_overview_tv.text    = movie.overview
        }
    }
    //endregion
}
