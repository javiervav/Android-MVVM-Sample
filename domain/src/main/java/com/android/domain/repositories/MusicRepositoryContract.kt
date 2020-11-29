package com.android.domain.repositories

import com.android.domain.Result
import com.android.domain.models.Album
import com.android.domain.models.Artist

interface MusicRepositoryContract {

    fun getArtistList(accessToken: String, text: String): Result<List<Artist>>

    fun getAlbumList(accessToken: String, text: String): List<Album>
}
