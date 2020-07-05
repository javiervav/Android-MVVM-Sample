package com.android.data.remote

import com.android.data.models.ArtistWrapper
import retrofit2.Call
import retrofit2.http.*

interface MusicApi {

    @GET("search?type=artist")
    fun getArtistList(@QueryMap params: Map<String, String>, @Header("Authorization") bearerToken: String): Call<ArtistWrapper>

    companion object {
        fun getBaseUrl() = "https://api.spotify.com/v1/"
    }
}
