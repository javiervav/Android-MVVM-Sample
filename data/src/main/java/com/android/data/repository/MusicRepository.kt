package com.android.data.repository

import com.android.domain.models.Album
import com.android.domain.models.Artist
import com.android.domain.repositories.MusicRepositoryContract

class MusicRepository: MusicRepositoryContract {

    override fun getAlbumList(accessToken: String, text: String): List<Album> {
        TODO("Not yet implemented")
    }

    override fun getArtistList(accessToken: String, text: String): List<Artist> {
        TODO("Not yet implemented")
    }
}
