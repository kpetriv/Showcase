package com.kirilpetriv.showcase.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kirilpetriv.network.service.ArtworkService
import com.kirilpetriv.showcase.core.ArtworkRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArtworkRepositoryImpl(
    private val artworkService: ArtworkService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ArtworkRepository {

    override fun getPaged(): Flow<PagingData<com.kirilpetriv.model.Artwork>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 20,
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { ArtworkPagingSource(service = artworkService) }
        ).flow.flowOn(dispatcher)
    }

    override fun getArtwork(id: Long) = flow {
        emit(com.kirilpetriv.model.Resource.Loading)
        runCatching {
            artworkService.getArtwork(id = id)
        }.onSuccess { result ->
            emit(com.kirilpetriv.model.Resource.Success(result.data.toModel()))
        }.onFailure {
            if (it is CancellationException) {
                throw it
            }
            emit(com.kirilpetriv.model.Resource.Failure(it))
        }
    }.flowOn(dispatcher)
}