package com.android.domain.models

import java.util.*

data class Album(
    val id: String,
    val artist: Artist,
    val imageUrl: String,
    val name: String,
    val releaseDate: Date,
    val totalTracks: Int
)
