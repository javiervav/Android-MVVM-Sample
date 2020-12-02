package com.android.data.repository

import com.android.data.remote.AuthApi
import com.android.domain.Result
import com.android.domain.repositories.AuthRepositoryContract
import java.net.UnknownHostException

class AuthRepository(
    private val authApi: AuthApi
) : AuthRepositoryContract {

    override fun getAccessToken(): Result<String> = try {
        val response = authApi.getAccessToken().execute()
        if (response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it.accessToken)
            } ?: Result.Failure
        } else {
            Result.Failure
        }
    } catch (e: UnknownHostException) {
        Result.Failure
    }
}
