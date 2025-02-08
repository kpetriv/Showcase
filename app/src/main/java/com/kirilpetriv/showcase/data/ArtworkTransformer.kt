package com.kirilpetriv.showcase.data

import com.kirilpetriv.showcase.network.ArtworkDto
import com.kirilpetriv.showcase.models.Artwork as ArtworkModel

internal fun ArtworkDto.toModel() = ArtworkModel(
    id = id,
    title = title ?: "Title is unknown",
    artist = artist ?: "Artist is unknown",
    description = description ?: "Description is unknown",
    imageUuid = imageUuid
)