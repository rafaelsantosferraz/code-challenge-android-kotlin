package com.arctouch.codechallenge.base.presentation.ui.adapter

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer


abstract class BaseListAdapter<T> (recyclerViewDiffCallback: DiffUtil.ItemCallback<T> = BaseRecyclerViewDiffCallback()): ListAdapter<T, BaseListAdapter.BaseViewHolder>(
    recyclerViewDiffCallback
) {

    protected var onItemClickListener: OnItemClickListener<T>? = null
    protected var onItemLongClickListener: OnItemLongClickListener<T>? = null
    protected var onItemReorderListener: OnItemReorderListener<T>? = null
    protected var onItemTouchListener: OnItemTouchListener? = null

    interface OnItemClickListener<T> {
        fun onItemClick(item: T, position: Int, view: View)
    }

    interface OnItemLongClickListener<T> {
        fun onItemLongClick(item: T, position: Int, view: View)
    }

    interface OnItemReorderListener <T>{
        fun onItemReorder(list: List<T>)
    }

    interface OnItemTouchListener{
        fun onItemTouch(holder: RecyclerView.ViewHolder, view: View, event: MotionEvent) : Boolean
    }




    // region Public methods -----------------------------------------------------------------------
    fun setOnItemClickListener(function: (item: T, position: Int, view: View) -> Unit) {
        onItemClickListener = object:
            OnItemClickListener<T> {
            override fun onItemClick(item: T, position: Int, view: View) {
                function.invoke(item, position, view)
            }
        }
    }

    fun setOnItemLongClickListener(function: (item: T, position: Int, view: View) -> Unit) {
        onItemLongClickListener = object:
            OnItemLongClickListener<T> {
            override fun onItemLongClick(item: T, position: Int, view: View) {
                function.invoke(item, position, view)
            }
        }
    }

    fun setOnTouchListener(function: (holder: RecyclerView.ViewHolder, view: View, event: MotionEvent) -> Boolean) {
        onItemTouchListener = object:
            OnItemTouchListener {
            override fun onItemTouch(holder: RecyclerView.ViewHolder, view: View, event: MotionEvent): Boolean {
                return function.invoke(holder, view, event)
            }
        }
    }

    fun setOnItemReorderListener(function: (list: List<T>) -> Unit) {
        onItemReorderListener = object:
            OnItemReorderListener<T> {
            override fun onItemReorder(list: List<T>) {
                function.invoke(list)
            }
        }
    }
    // endregion


    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) = viewHolder.bind(position)


    // region ViewHolders --------------------------------------------------------------------------
    abstract class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view), LayoutContainer{

        override val containerView: View?
            get() = view

        abstract fun bind(position: Int)
    }
    // endregion
}