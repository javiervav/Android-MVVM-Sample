package com.android.domain.repositories

import com.android.domain.Result

interface AuthRepositoryContract {

    fun getAccessToken(): Result<String>
}
