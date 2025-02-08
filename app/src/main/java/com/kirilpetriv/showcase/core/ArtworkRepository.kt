package com.kirilpetriv.showcase.core

import androidx.paging.PagingData
import com.kirilpetriv.showcase.models.Artwork
import com.kirilpetriv.showcase.models.Resource
import kotlinx.coroutines.flow.Flow

interface ArtworkRepository {
    fun getPaged(): Flow<PagingData<Artwork>>

    fun getArtwork(id: Long): Flow<Resource<Artwork>>
}