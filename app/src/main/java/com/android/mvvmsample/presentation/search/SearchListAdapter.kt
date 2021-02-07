package com.android.mvvmsample.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.models.SearchItem
import com.android.mvvmsample.databinding.LayoutItemArtistSearchBinding
import com.android.mvvmsample.shared.networkImageLoader.NetworkImageLoader


class SearchListAdapter(
    private val searchType: SearchType,
    private val networkImageLoader: NetworkImageLoader
) : ListAdapter<SearchItem, SearchListAdapter.SearchListViewHolder>(SearchListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val itemView = LayoutItemArtistSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchItemFactory.createViewHolder(searchType, itemView)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.bind(getItem(position), networkImageLoader)
    }

    abstract class SearchListViewHolder(binding: LayoutItemArtistSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(searchItem: SearchItem, networkImageLoader: NetworkImageLoader)
    }

    class ArtistListViewHolder(private val binding: LayoutItemArtistSearchBinding) : SearchListViewHolder(binding) {
        override fun bind(searchItem: SearchItem, networkImageLoader: NetworkImageLoader) {
            val artist = searchItem as SearchItem.Artist
            binding.searchType = SearchType.ARTIST
            binding.artist = artist
            binding.networkImageLoader = networkImageLoader
        }
    }

    class AlbumListViewHolder(private val binding: LayoutItemArtistSearchBinding) : SearchListViewHolder(binding) {
        override fun bind(searchItem: SearchItem, networkImageLoader: NetworkImageLoader) {
            val album = searchItem as SearchItem.Album
            binding.searchType = SearchType.ALBUM
            binding.album = album
            binding.networkImageLoader = networkImageLoader
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
    fun createViewHolder(
        searchType: SearchType,
        binding: LayoutItemArtistSearchBinding
    ): SearchListAdapter.SearchListViewHolder {
        return when (searchType) {
            SearchType.ARTIST -> SearchListAdapter.ArtistListViewHolder(binding)
            SearchType.ALBUM -> SearchListAdapter.AlbumListViewHolder(binding)
        }
    }
}
