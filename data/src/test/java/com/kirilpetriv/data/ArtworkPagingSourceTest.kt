package com.kirilpetriv.data

import androidx.paging.PagingSource
import com.kirilpetriv.data.paging.ArtworkPagingSource
import com.kirilpetriv.data.templates.artworkDtoTemplate
import com.kirilpetriv.data.transformer.toModel
import com.kirilpetriv.model.Artwork
import com.kirilpetriv.model.NetworkError
import com.kirilpetriv.network.dto.Page
import com.kirilpetriv.network.service.ArtworkService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ArtworkPagingSourceTest {

    private val mockService = mockk<ArtworkService>()
    private val pagingSource = ArtworkPagingSource(mockService)

    @Test
    fun `load returns page when on successful load of data`() = runTest {
        val artworks = listOf(artworkDtoTemplate.copy(id = 1), artworkDtoTemplate.copy(id = 2))
        coEvery { mockService.getArtworks(page = 1) } returns Page(data = artworks)

        val expectedResult = PagingSource.LoadResult.Page(
            data = artworks.map { it.toModel() },
            prevKey = null,
            nextKey = 2
        )

        val actualResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `load returns error when exception is thrown`() = runTest {
        val exception = NetworkError()
        coEvery { mockService.getArtworks(page = 1) } throws exception

        val expectedResult = PagingSource.LoadResult.Error<Int, Artwork>(exception)

        val actualResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        Assertions.assertEquals(expectedResult, actualResult)
    }
}