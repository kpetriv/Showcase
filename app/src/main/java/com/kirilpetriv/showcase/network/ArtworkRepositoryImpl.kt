package com.kirilpetriv.showcase.network

import com.kirilpetriv.showcase.core.ArtworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtworkRepositoryImpl(
    private val artworkService: ArtworkService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ArtworkRepository {
    override suspend fun getArtworks() = withContext(dispatcher) {
        artworkService.getArtworks().data.map {
            it.toModel()
        }
    }
}