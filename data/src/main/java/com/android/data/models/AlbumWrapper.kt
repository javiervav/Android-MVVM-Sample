package com.android.data.models

import com.android.domain.models.SearchItem
import com.google.gson.annotations.SerializedName

data class AlbumWrapper(
    val albums: MusicPagination<Album>
)

data class Album(
    val id: String,
    val name: String,
    val artists: List<AlbumArtist>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("total_tracks") val totalTracks: Short,
    @SerializedName("available_markets") val availableMarkets: List<String>,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    val images: List<Image>
) {
    fun toDomainAlbum(): SearchItem.Album = SearchItem.Album(
        id = id,
        name = name,
        artist = artists[0].name,
        imageUrl = if (images.isNotEmpty()) {
            images[0].url
        } else null,
        releaseDate = releaseDate,
        totalTracks = totalTracks
    )
}

data class AlbumArtist(
    val id: String,
    val name: String,
    @SerializedName("external_urls") val externalUrls: ExternalUrls
)
