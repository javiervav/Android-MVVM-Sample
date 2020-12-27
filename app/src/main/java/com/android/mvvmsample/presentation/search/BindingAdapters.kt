package com.android.mvvmsample.presentation.search

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.models.SearchItem

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<SearchItem>?) {
    if (items !== null) {
        (adapter as SearchListAdapter).submitList(items)
    }
}

@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
