package com.kirilpetriv.data.transformer

import com.kirilpetriv.network.dto.ArtworkDto
import com.kirilpetriv.model.Artwork as ArtworkModel

internal fun ArtworkDto.toModel() = ArtworkModel(
    id = id,
    title = title ?: "Title is unknown",
    artist = artist ?: "Artist is unknown",
    description = description ?: "Description is unknown",
    imageUuid = imageUuid
)