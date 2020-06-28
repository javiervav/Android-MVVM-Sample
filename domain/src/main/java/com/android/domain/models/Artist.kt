package com.android.domain.models

data class Artist(
    val id: String,
    val name: String,
    val followers: Int?,
    val genres: List<String>?,
    val imageUrl: String?,
    val popularity: Int
)
