package com.yabahddou.pagersnaphelper

import android.graphics.Rect
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView

/**
 ***************************************
 * Created by Abdelhadi on 2019-05-10.
 ***************************************
 */
class PagerMarginItemDecoration(private val horizontalMargin: Int = 0, private val adjacentVisibleSize: Int = 0) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val childCount = state.itemCount

        val firstItem = position == 0
        val lastItem = position == childCount - 1

        val screenSize = parent.context.screenSize()
        var itemWidth = screenSize.widthPx - 2 * adjacentVisibleSize - 4 * horizontalMargin

        if (firstItem || lastItem) {
            itemWidth = screenSize.widthPx - adjacentVisibleSize - 4 * horizontalMargin
        }

        if (firstItem && lastItem) {
            itemWidth = screenSize.widthPx - 4 * horizontalMargin
        }

        view.updateLayoutParams {
            width = itemWidth
            height = RecyclerView.LayoutParams.MATCH_PARENT
        }

        with(outRect) {
            left = if (firstItem) 2 * horizontalMargin else horizontalMargin
            right = if (lastItem) 2 * horizontalMargin else horizontalMargin
        }
    }
}