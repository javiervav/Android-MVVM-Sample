package com.android.data.models

data class MusicPagination<T>(
    val items: List<T>,
    val limit: Short,
    val offset: Short,
    val total: Short,
    val next: String?,
    val previous: String?
)

data class ExternalUrls(
    val spotify: String
)

data class Image(
    val height: Short,
    val width: Short,
    val url: String
)
