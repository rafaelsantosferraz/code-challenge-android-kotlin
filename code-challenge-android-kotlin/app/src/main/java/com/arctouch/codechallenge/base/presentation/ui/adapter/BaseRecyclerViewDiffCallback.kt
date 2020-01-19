package com.arctouch.codechallenge.base.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil

class BaseRecyclerViewDiffCallback <T>  : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = (oldItem == newItem)


    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = (oldItem == newItem)
}