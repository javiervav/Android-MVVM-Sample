package com.android.data.models

import com.google.gson.annotations.SerializedName

data class ArtistWrapper(
    val artists: MusicPagination<Artist>
)

data class Artist(
    val id: String,
    val name: String,
    val popularity: Short,
    val followers: Followers,
    val genres: List<String>,
    val images: List<Image>,
    @SerializedName("external_urls") val externalUrls: ExternalUrls
) {
    fun toDomainArtist(): com.android.domain.models.Artist = com.android.domain.models.Artist(
        id = id,
        name = name,
        followers = followers.total,
        genres = genres,
        imageUrl = if (images.isNotEmpty()) {
            images[0].url
        } else null,
        popularity = popularity,
        spotifyUrl = externalUrls.spotify
    )
}

data class Followers(
    val total: Int
)
