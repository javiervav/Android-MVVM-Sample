package com.android.domain.repositories

import com.android.domain.models.Album
import com.android.domain.models.Artist

interface MusicRepositoryContract {

    fun getAlbumList(accessToken: String, text: String): List<Album>

    fun getArtistList(accessToken: String, text: String): List<Artist>
}
