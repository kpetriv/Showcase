package com.kirilpetriv.showcase.network

import com.kirilpetriv.showcase.models.Artwork
import com.kirilpetriv.showcase.models.NetworkError
import com.kirilpetriv.showcase.models.Resource
import com.kirilpetriv.showcase.templates.artworkDtoTemplate
import com.kirilpetriv.showcase.templates.artworkModelTemplate
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArtworkRepositoryImplTest {

    private val artworkService: ArtworkService = mockk()

    private fun artworkRepository(scheduler: TestCoroutineScheduler) = ArtworkRepositoryImpl(
        artworkService = artworkService,
        dispatcher = StandardTestDispatcher(scheduler = scheduler)
    )

    @BeforeEach
    fun init() {
        clearAllMocks()
    }

    @Test
    fun `with successful request properly return a converted model`() = runTest {
        coEvery {
            artworkService.getArtworks(any(), any(), any())
        } returns Page(
            data = listOf(artworkDtoTemplate),
        )

        val emits = mutableListOf<Resource<List<Artwork>>>()
        artworkRepository(testScheduler).getArtworks().toList(emits)

        assertEquals(
            expected = listOf(
                Resource.Loading,
                Resource.Success(listOf(artworkModelTemplate))
            ),
            actual = emits
        )
    }

    @Test
    fun `with error properly propagates the error`() = runTest {
        val error = NetworkError()
        coEvery { artworkService.getArtworks() } throws error

        val emits = mutableListOf<Resource<List<Artwork>>>()
        artworkRepository(testScheduler).getArtworks().toList(emits)

        assertEquals(
            expected = listOf(
                Resource.Loading,
                Resource.Failure(error),
            ) as List<Resource<List<Artwork>>>,
            actual = emits
        )
    }
}