package com.kirilpetriv.showcase.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kirilpetriv.model.Artwork
import com.kirilpetriv.network.service.ArtworkService

/**
 * Paging source for fetching artworks.
 * As the dataset tends to change frequently without having specific sorting, remoteMediator
 * cannot be used to retain data consistency as the DB stored page key references and it might
 * cause issues when the dataset changes.
 */
class ArtworkPagingSource(
    private val service: ArtworkService
) : PagingSource<Int, Artwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artwork> {
        val page = params.key ?: 1
        return try {
            val data = service.getArtworks(page = page).data.map { it.toModel() }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Artwork>): Int? =
        state.anchorPosition
}