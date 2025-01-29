package com.kirilpetriv.showcase.network

import com.kirilpetriv.showcase.core.Artwork as ArtworkModel
import com.kirilpetriv.showcase.network.Artwork as ArtworkDto

internal fun ArtworkDto.toModel() = ArtworkModel(
    id = id,
    title = title,
    artist = artist,
    description = description,
    imageUuid = imageUuid
)