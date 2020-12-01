package com.android.organizer.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.models.Artist
import com.android.organizer.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.layout_item_artist_search.view.*


class SearchListAdapter : ListAdapter<Artist, SearchListAdapter.SearchListViewHolder>(SearchListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_artist_search, parent, false)
        return SearchListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(artist: Artist) {
            Glide.with(itemView.context)
                .load(artist.imageUrl)
                .transform(CenterCrop(), RoundedCorners(itemView.context.resources.getDimensionPixelSize(R.dimen.default_corner_radius)))
                .into(itemView.searchItemImage)
            itemView.searchItemText.text = artist.name
            itemView.searchItemFollowers.text = artist.genres?.joinToString()
        }
    }
}

class SearchListDiffCallback : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
        oldItem == newItem
}
