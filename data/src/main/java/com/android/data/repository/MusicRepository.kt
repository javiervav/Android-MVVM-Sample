package com.android.data.repository

import com.android.data.remote.MusicApi
import com.android.domain.models.Album
import com.android.domain.models.Artist
import com.android.domain.repositories.MusicRepositoryContract

class MusicRepository(
    private val musicApi: MusicApi
) : MusicRepositoryContract {

    override fun getAlbumList(accessToken: String, text: String): List<Album> {
        TODO("Not yet implemented")
    }

    override fun getArtistList(accessToken: String, text: String): List<Artist> {
        val queryParams = getArtistListQueryParams(text)
        val response = musicApi.getArtistList(queryParams, "Bearer $accessToken").execute()
        return if (response.isSuccessful) {
            response.body()?.artists?.items?.map { it.toDomainArtist() }.orEmpty().sortedByDescending { it.popularity }.take(MAX_RETURN_VALUES)
        } else {
            emptyList()
        }
    }

    private fun getArtistListQueryParams(text: String): Map<String, String> =
        HashMap<String, String>().apply {
            this[TEXT_KEY] = text
            this[TYPE_KEY] = TYPE_ARTIST_VALUE
        }

    companion object {
        private const val TEXT_KEY = "q"
        private const val TYPE_KEY = "type"
        private const val TYPE_ARTIST_VALUE = "artist"

        private const val MAX_RETURN_VALUES = 10
    }
}
