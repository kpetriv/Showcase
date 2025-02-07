package com.kirilpetriv.showcase.models

data class Artwork(
    val id: Long,
    val title: String,
    val artist: String,
    val description: String,
    val imageUuid: String?,
)