package com.arctouch.codechallenge.base.presentation.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class BaseRecyclerViewItemDecorator(private val padding : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemCount = state.itemCount

        val itemPosition = parent.getChildAdapterPosition(view)

        var left = 0
        var top = 0
        var right = 0
        var bottom = 0



        if(itemCount > 0) {
            when(itemPosition){
                0 -> { // first item
                    top = padding
                    bottom = padding
                }
                (itemCount - 1) -> { // last item
                    bottom = padding
                }
                else -> {
                    bottom = padding
                }
            }
        }

        outRect.set(left,top,right, bottom)
    }
}