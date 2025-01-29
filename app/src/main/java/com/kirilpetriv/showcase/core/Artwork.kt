package com.kirilpetriv.showcase.core

data class Artwork(
    val id: Long,
    val title: String,
    val artist: String,
    val description: String,
    val imageUuid: String?,
)