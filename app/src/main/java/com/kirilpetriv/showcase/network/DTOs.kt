package com.kirilpetriv.showcase.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Page<T>(
    val data: List<T>,
)

@Serializable
data class Result<T>(
    val data: T,
)

@Serializable
data class ArtworkDto(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String? = null,
    @SerialName("artist_display")
    val artist: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("image_id")
    val imageUuid: String? = null,
)