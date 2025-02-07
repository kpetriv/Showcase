package com.kirilpetriv.showcase.network

import com.kirilpetriv.showcase.models.Artwork as ArtworkModel
import com.kirilpetriv.showcase.network.Artwork as ArtworkDto

internal fun ArtworkDto.toModel() = ArtworkModel(
    id = id,
    title = title ?: "Title is unknown",
    artist = artist ?: "Artist is unknown",
    description = description ?: "Description is unknown",
    imageUuid = imageUuid
)