package com.android.domain

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    object Failure : Result<Nothing>()
}
