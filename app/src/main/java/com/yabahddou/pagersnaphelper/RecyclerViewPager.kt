package com.yabahddou.pagersnaphelper

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

/**
 ***************************************
 * Created by Abdelhadi on 2019-06-01.
 ***************************************
 */
open class RecyclerViewPager : RecyclerView {

    var currentItem = NO_POSITION
        protected set

    private var pageChangeListeners: MutableList<(Int) -> Unit> = mutableListOf()

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        PagerSnapHelper().attachToRecyclerView(this)

        val linearLayoutManager = layoutManager as LinearLayoutManager
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val currentVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                if (currentVisibleItem != NO_POSITION && currentItem != currentVisibleItem) {
                    onPageChanged(currentVisibleItem)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == SCROLL_STATE_IDLE) {
                    val currentVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                    if (currentVisibleItem != NO_POSITION && currentItem != currentVisibleItem) {
                        onPageChanged(currentVisibleItem)
                    }
                }
            }
        })

        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.RecyclerViewPager)
            val pageMargin = ta.getDimensionPixelSize(R.styleable.RecyclerViewPager_rvp_pageMargin, 0)
            val adjacentVisibleSize = ta.getDimensionPixelSize(R.styleable.RecyclerViewPager_rvp_adjacentVisibleSize, 0)
            setPageMargin(pageMargin, adjacentVisibleSize)
            ta.recycle()
        }
    }

    private fun onPageChanged(currentItem: Int) {
        this.currentItem = currentItem
        notifyListenersWithNewPage()
    }

    private fun notifyListenersWithNewPage() {
        pageChangeListeners.forEach { listener -> listener(currentItem) }
    }

    /**
     * To avoid adding margin decorator multiple times
     */
    private fun removePreviousMarginDecorator() {
        for (i in 0 until itemDecorationCount) {
            val itemDecoration = getItemDecorationAt(i)
            if (itemDecoration is PagerMarginItemDecoration) {
                removeItemDecorationAt(i)
                break
            }
        }
    }

    override fun addItemDecoration(decor: ItemDecoration, index: Int) {
        removePreviousMarginDecorator()
        super.addItemDecoration(decor, index)
    }

    /**
     * Set the margin between pages.
     * [pageMargin] Distance between adjacent pages in pixels
     * [adjacentVisibleSize] Size of the adjacent pages visible part in pixels
     */
    fun setPageMargin(pageMargin: Int = 0, adjacentVisibleSize: Int = 0) {
        addItemDecoration(PagerMarginItemDecoration(pageMargin, adjacentVisibleSize))
    }

    /**
     * Add page change listener
     */
    fun addPageChangeListener(listener: (currentIndex: Int) -> Unit) {
        this.pageChangeListeners.add(listener)
    }
}