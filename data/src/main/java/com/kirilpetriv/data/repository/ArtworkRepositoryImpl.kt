package com.kirilpetriv.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kirilpetriv.data.paging.ArtworkPagingSource
import com.kirilpetriv.data.transformer.toModel
import com.kirilpetriv.domain.repository.ArtworkRepository
import com.kirilpetriv.model.Artwork
import com.kirilpetriv.model.Resource
import com.kirilpetriv.network.service.ArtworkService
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

    override fun getPaged(): Flow<PagingData<Artwork>> {
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
        emit(Resource.Loading)
        runCatching {
            artworkService.getArtwork(id = id)
        }.onSuccess { result ->
            emit(Resource.Success(result.data.toModel()))
        }.onFailure {
            if (it is CancellationException) {
                throw it
            }
            emit(Resource.Failure(it))
        }
    }.flowOn(dispatcher)
}