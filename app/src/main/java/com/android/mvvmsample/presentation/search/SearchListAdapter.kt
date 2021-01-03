package com.android.mvvmsample.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.models.SearchItem
import com.android.mvvmsample.R
import com.android.mvvmsample.shared.networkImageLoader.NetworkImageLoader
import com.android.mvvmsample.utils.thumbnailUrl
import kotlinx.android.synthetic.main.layout_item_artist_search.view.*


class SearchListAdapter(
    private val searchType: SearchType,
    private val networkImageLoader: NetworkImageLoader
) : ListAdapter<SearchItem, SearchListAdapter.SearchListViewHolder>(SearchListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_artist_search, parent, false)
        return SearchItemFactory.createViewHolder(searchType, itemView)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.bind(getItem(position), networkImageLoader)
    }

    abstract class SearchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(searchItem: SearchItem, networkImageLoader: NetworkImageLoader)
    }

    class ArtistListViewHolder(itemView: View) : SearchListViewHolder(itemView) {
        override fun bind(searchItem: SearchItem, networkImageLoader: NetworkImageLoader) {
            val artist = searchItem as SearchItem.Artist
            networkImageLoader.loadImageUrl(artist.imageUrl, thumbnailUrl, itemView.searchItemImage)
            itemView.searchItemTitle.text = artist.name
            itemView.searchItemText.text = artist.genres?.joinToString()
        }
    }

    class AlbumListViewHolder(itemView: View) : SearchListViewHolder(itemView) {
        override fun bind(searchItem: SearchItem, networkImageLoader: NetworkImageLoader) {
            val album = searchItem as SearchItem.Album
            networkImageLoader.loadImageUrl(album.imageUrl, thumbnailUrl, itemView.searchItemImage)
            itemView.searchItemTitle.text = album.name
            itemView.searchItemText.text = album.artist
        }
    }
}

class SearchListDiffCallback : DiffUtil.ItemCallback<SearchItem>() {
    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
        oldItem == newItem
}

object SearchItemFactory {
    fun createViewHolder(searchType: SearchType, itemView: View): SearchListAdapter.SearchListViewHolder {
        return when (searchType) {
            SearchType.ARTIST -> SearchListAdapter.ArtistListViewHolder(itemView)
            SearchType.ALBUM -> SearchListAdapter.AlbumListViewHolder(itemView)
        }
    }
}
