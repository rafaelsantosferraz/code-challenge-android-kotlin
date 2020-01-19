package com.arctouch.codechallenge.home.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.presentation.ui.adapter.BaseListAdapter
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter : BaseListAdapter<Movie>() {

    private val movieImageUrlBuilder = MovieImageUrlBuilder()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))


    inner class ItemViewHolder(view: View) : BaseListAdapter.BaseViewHolder(view){

        override fun bind(position: Int){
            val movie = getItem(position)

            movie?.apply {
                view.titleTextView.text = title
                view.genresTextView.text = genres?.joinToString(separator = ", ") { it.name }
                view.releaseDateTextView.text = releaseDate

                Glide.with(view)
                        .load(posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                        .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                        .into(view.posterImageView)
                view.setOnClickListener { onItemClickListener?.onItemClick(this, position, view) }
            }
        }
    }
}
