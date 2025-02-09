package com.kirilpetriv.showcase.core

import androidx.paging.PagingData
import com.kirilpetriv.model.Artwork
import com.kirilpetriv.model.Resource
import kotlinx.coroutines.flow.Flow

interface ArtworkRepository {
    fun getPaged(): Flow<PagingData<Artwork>>

    fun getArtwork(id: Long): Flow<Resource<Artwork>>
}