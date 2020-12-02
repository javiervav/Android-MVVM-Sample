package com.android.data.remote

import com.android.data.models.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @Headers("Authorization: Basic $ENCODED_CLIENT")
    @POST("token")
    fun getAccessToken(@Field("grant_type") grantType: String = GRANT_TYPE): Call<AccessToken>

    companion object {
        private const val ENCODED_CLIENT = // <base64 encoded client_id:client_secret>
            "YjI2YmQxYTY3MDdmNDRiYjg0NGQ3NTMzNTA2ZDU2Mzg6M2VjNzhjNjQ0MzRiNDEwZjkxYmQ5ZWViMzNhNTQ1NWE="
        private const val GRANT_TYPE = "client_credentials"

        fun getBaseUrl() = "https://accounts.spotify.com/api/"
    }
}
