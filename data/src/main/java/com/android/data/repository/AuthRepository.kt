package com.android.data.repository

import com.android.data.remote.auth.AuthApi
import com.android.domain.repositories.AuthRepositoryContract

class AuthRepository(
    private val authApi: AuthApi
) : AuthRepositoryContract {

    // TODO: Check no connectivity, since it's crashing if I disable Wi-Fi

    override fun getAccessToken(): String? {
        val response = authApi.getAccessToken().execute()
        return if (response.isSuccessful) {
            response.body()?.accessToken
        } else {
            null
        }
    }
}
