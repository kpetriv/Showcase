package com.kirilpetriv.artwork.templates


import com.kirilpetriv.model.Artwork as ArtworkModel

val artworkModelTemplate: ArtworkModel
    get() = ArtworkModel(
        id = 1,
        title = "title",
        imageUuid = "6c046e7f-614b-0060-2e7b-e12779ae48b6",
        artist = "artist",
        description = "description"
    )