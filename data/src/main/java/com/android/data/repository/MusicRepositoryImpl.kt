package com.android.data.repository

import com.android.data.remote.MusicApi
import com.android.domain.Result
import com.android.domain.models.SearchItem
import com.android.domain.repositories.MusicRepositoryContract

class MusicRepositoryImpl(
    private val musicApi: MusicApi
) : MusicRepositoryContract {

    override fun getArtistList(accessToken: String, text: String, offset: Int): Result<List<SearchItem.Artist>> {
        val queryParams = getArtistListQueryParams(text, offset)
        val response = musicApi.getArtistList(queryParams, "Bearer $accessToken").execute()
        return if (response.isSuccessful) {
            val artistList = response.body()?.artists?.items
                ?.sortedByDescending { it.followers.total }
                ?.map { it.toDomainArtist() }
                .orEmpty()
            Result.Success(artistList)
        } else {
            Result.Failure
        }
    }

    override fun getAlbumList(accessToken: String, text: String): Result<List<SearchItem.Album>> {
        val queryParams = getAlbumListQueryParams(text)
        val response = musicApi.getAlbumList(queryParams, "Bearer $accessToken").execute()
        return if (response.isSuccessful) {
            // This endpoint returns albums released by bands with "text" in its name, but not in album's name.
            // That's why filter is added.
            val albumList = response.body()?.albums?.items
                ?.filter { it.name.contains(text, true) }
                ?.map { it.toDomainAlbum() }.orEmpty()
            Result.Success(albumList)
        } else {
            Result.Failure
        }
    }

    private fun getArtistListQueryParams(text: String, offset: Int): Map<String, String> =
        HashMap<String, String>().apply {
            this[TEXT_KEY] = text
            this[TYPE_KEY] = TYPE_ARTIST_VALUE
            this[TYPE_OFFSET] = offset.toString()
        }

    private fun getAlbumListQueryParams(text: String): Map<String, String> =
        HashMap<String, String>().apply {
            this[TEXT_KEY] = text
            this[TYPE_KEY] = TYPE_ALBUM_VALUE
        }

    companion object {
        private const val TEXT_KEY = "q"
        private const val TYPE_KEY = "type"
        private const val TYPE_OFFSET = "offset"
        private const val TYPE_ARTIST_VALUE = "artist"
        private const val TYPE_ALBUM_VALUE = "album"
    }
}
