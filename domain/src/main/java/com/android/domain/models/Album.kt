package com.android.domain.models

import java.util.*

data class Album(
    val id: String,
    val name: String,
    val artist: String,
    val imageUrl: String?,
    val releaseDate: String,
    val totalTracks: Short
)
