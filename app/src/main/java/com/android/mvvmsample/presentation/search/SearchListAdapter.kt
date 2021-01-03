package com.android.mvvmsample.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.models.SearchItem
import com.android.mvvmsample.R
import com.android.mvvmsample.utils.thumbnailUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.layout_item_artist_search.view.*


class SearchListAdapter(
    private val searchType: SearchType
) : ListAdapter<SearchItem, SearchListAdapter.SearchListViewHolder>(SearchListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_artist_search, parent, false)
        return SearchItemFactory.createViewHolder(searchType, itemView)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract class SearchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val roundedCornerRadius =
            itemView.context.resources.getDimensionPixelSize(R.dimen.default_corner_radius)

        abstract fun bind(searchItem: SearchItem)

        fun setImage(imageUrl: String?) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .thumbnail(getThumbnail())
                .transform(CenterCrop(), RoundedCorners(roundedCornerRadius))
                .into(itemView.searchItemImage)
        }

        private fun getThumbnail() =
            Glide.with(itemView.context)
                .load(thumbnailUrl)
                .transform(CenterCrop(), RoundedCorners(roundedCornerRadius))
    }

    class ArtistListViewHolder(itemView: View) : SearchListViewHolder(itemView) {
        override fun bind(searchItem: SearchItem) {
            val artist = searchItem as SearchItem.Artist
            setImage(artist.imageUrl)
            itemView.searchItemTitle.text = artist.name
            itemView.searchItemText.text = artist.genres?.joinToString()
        }
    }

    class AlbumListViewHolder(itemView: View) : SearchListViewHolder(itemView) {
        override fun bind(searchItem: SearchItem) {
            val album = searchItem as SearchItem.Album
            setImage(album.imageUrl)
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
