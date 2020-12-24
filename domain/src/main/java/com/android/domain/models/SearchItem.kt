package com.android.domain.models

sealed class SearchItem {

    abstract val id: String
    abstract val name: String

    data class Artist(
        override val id: String,
        override val name: String,
        val followers: Int?,
        val genres: List<String>?,
        val imageUrl: String?,
        val popularity: Short,
        val spotifyUrl: String
    ) : SearchItem()

    data class Album(
        override val id: String,
        override val name: String,
        val artist: String,
        val imageUrl: String?,
        val releaseDate: String,
        val totalTracks: Short
    ) : SearchItem()
}
