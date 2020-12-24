package com.android.domain.repositories

import com.android.domain.Result
import com.android.domain.models.SearchItem

interface MusicRepositoryContract {

    fun getArtistList(accessToken: String, text: String): Result<List<SearchItem.Artist>>

    fun getAlbumList(accessToken: String, text: String): Result<List<SearchItem.Album>>
}
