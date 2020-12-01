package com.android.data.repository

import com.android.data.remote.MusicApi
import com.android.domain.Result
import com.android.domain.models.Album
import com.android.domain.models.Artist
import com.android.domain.repositories.MusicRepositoryContract

class MusicRepository(
    private val musicApi: MusicApi
) : MusicRepositoryContract {

    override fun getArtistList(accessToken: String, text: String): Result<List<Artist>> {
        val queryParams = getArtistListQueryParams(text)
        val response = musicApi.getArtistList(queryParams, "Bearer $accessToken").execute()
        return if (response.isSuccessful) {
            val artistList = response.body()?.artists?.items
                ?.sortedByDescending { it.followers.total }
                ?.take(MAX_ARTISTS_RETURN_VALUES)
                ?.map { it.toDomainArtist() }
                .orEmpty()
            Result.Success(artistList)
        } else {
            Result.Failure
        }
    }

    override fun getAlbumList(accessToken: String, text: String): List<Album> {
        val queryParams = getAlbumListQueryParams(text)
        val response = musicApi.getAlbumList(queryParams, "Bearer $accessToken").execute()
        return if (response.isSuccessful) {
            // This endpoint returns albums released by bands with "text" in its name, but not in album's name. That's why filter is added.
            response.body()?.albums?.items?.filter { it.name.contains(text, true) }?.map { it.toDomainAlbum() }.orEmpty().take(MAX_ALBUMS_RETURN_VALUES)
        } else {
            emptyList()
        }
    }

    private fun getArtistListQueryParams(text: String): Map<String, String> =
        HashMap<String, String>().apply {
            this[TEXT_KEY] = text
            this[TYPE_KEY] = TYPE_ARTIST_VALUE
        }

    private fun getAlbumListQueryParams(text: String): Map<String, String> =
        HashMap<String, String>().apply {
            this[TEXT_KEY] = text
            this[TYPE_KEY] = TYPE_ALBUM_VALUE
        }

    companion object {
        private const val TEXT_KEY = "q"
        private const val TYPE_KEY = "type"
        private const val TYPE_ARTIST_VALUE = "artist"
        private const val TYPE_ALBUM_VALUE = "album"

        private const val MAX_ARTISTS_RETURN_VALUES = 10
        private const val MAX_ALBUMS_RETURN_VALUES = 20
    }
}
