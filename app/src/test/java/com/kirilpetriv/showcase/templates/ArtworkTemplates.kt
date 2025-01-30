package com.kirilpetriv.showcase.templates

import com.kirilpetriv.showcase.network.Artwork as ArtworkDto
import com.kirilpetriv.showcase.core.Artwork as ArtworkModel

val artworkModelTemplate: ArtworkModel
    get() = ArtworkModel(
        id = 1,
        title = "title",
        imageUuid = "6c046e7f-614b-0060-2e7b-e12779ae48b6",
        artist = "artist",
        description = "description"
    )

val artworkDtoTemplate: ArtworkDto
    get() = ArtworkDto(
        id = 1,
        title = "title",
        imageUuid = "6c046e7f-614b-0060-2e7b-e12779ae48b6",
        artist = "artist",
        description = "description"
    )