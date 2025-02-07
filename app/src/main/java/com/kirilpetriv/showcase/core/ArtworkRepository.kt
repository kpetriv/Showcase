package com.kirilpetriv.showcase.core

import com.kirilpetriv.showcase.models.Artwork
import com.kirilpetriv.showcase.models.Resource
import kotlinx.coroutines.flow.Flow

interface ArtworkRepository {
    /**
     * @return Flow of a Result of a list of [Artwork] objects.
     */
    fun getArtworks(): Flow<Resource<List<Artwork>>>
}