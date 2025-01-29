package com.kirilpetriv.showcase.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Page<T>(
    val data: List<T>,
)

@Serializable
data class Artwork(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("artist_display")
    val artist: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_id")
    val imageUuid: String,
)