package com.android.data.remote

import com.android.data.models.AlbumWrapper
import com.android.data.models.ArtistWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface MusicApi {

    @GET("search?type=artist")
    fun getArtistList(
        @QueryMap params: Map<String, String>,
        @Header("Authorization") bearerToken: String
    ): Call<ArtistWrapper>

    @GET("search?type=album")
    fun getAlbumList(
        @QueryMap params: Map<String, String>,
        @Header("Authorization") bearerToken: String
    ): Call<AlbumWrapper>

    companion object {
        fun getBaseUrl() = "https://api.spotify.com/v1/"
    }
}
