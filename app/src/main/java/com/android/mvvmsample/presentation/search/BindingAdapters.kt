package com.android.mvvmsample.presentation.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.models.SearchItem
import com.android.mvvmsample.shared.networkImageLoader.NetworkImageLoader
import com.android.mvvmsample.utils.thumbnailUrl

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

@BindingAdapter("searchType", "artist", "album", requireAll = true)
fun TextView.setSubtitle(searchType: SearchType, artist: SearchItem.Artist?, album: SearchItem.Album?) {
    text = when (searchType) {
        SearchType.ARTIST -> artist?.genres?.joinToString()
        SearchType.ALBUM -> album?.artist
    }
}

@BindingAdapter("networkImageLoader", "imageUrl")
fun ImageView.loadImage(networkImageLoader: NetworkImageLoader, imageUrl: String?) {
    networkImageLoader.loadImageUrl(imageUrl, thumbnailUrl, this)
}
