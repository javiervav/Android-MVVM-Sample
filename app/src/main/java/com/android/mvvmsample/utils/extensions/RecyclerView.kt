package com.android.mvvmsample.utils.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addLoadMoreListener(threshold: Int = 5, listener: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = layoutManager as LinearLayoutManager
            if (layoutManager.isCloseToTheEnd(threshold)) {
                listener()
            }
        }
    })
}

private fun LinearLayoutManager.isCloseToTheEnd(threshold: Int): Boolean {
    val visibleItemCount = childCount
    val totalItemCount = itemCount
    val firstVisibleItemPosition = findFirstVisibleItemPosition()
    val lastVisibleItemCount = visibleItemCount + firstVisibleItemPosition

    return lastVisibleItemCount >= totalItemCount - threshold
}
