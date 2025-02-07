package com.kirilpetriv.showcase.network

import com.kirilpetriv.showcase.core.ArtworkRepository
import com.kirilpetriv.showcase.models.Resource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArtworkRepositoryImpl(
    private val artworkService: ArtworkService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ArtworkRepository {
    override fun getArtworks() = flow {
        emit(Resource.Loading)
        runCatching {
            artworkService.getArtworks()
        }.onSuccess { result ->
            emit(Resource.Success(result.data.map { it.toModel() }))
        }.onFailure {
            if (it is CancellationException) {
                throw it
            }
            emit(Resource.Failure(it))
        }
    }.flowOn(dispatcher)
}