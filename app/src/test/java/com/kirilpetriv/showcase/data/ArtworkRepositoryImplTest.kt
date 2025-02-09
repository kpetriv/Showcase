package com.kirilpetriv.showcase.data

import com.kirilpetriv.network.dto.Result
import com.kirilpetriv.model.Artwork
import com.kirilpetriv.model.NetworkError
import com.kirilpetriv.model.Resource
import com.kirilpetriv.network.service.ArtworkService
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
import org.junit.jupiter.api.Nested
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

    @Nested
    inner class GetArtwork {
        @Test
        fun `with successful request properly return a converted model`() = runTest {
            coEvery {
                artworkService.getArtwork(any())
            } returns Result(
                data = artworkDtoTemplate,
            )

            val emits =
                mutableListOf<Resource<Artwork>>()
            artworkRepository(testScheduler).getArtwork(id = 123).toList(emits)

            assertEquals(
                expected = listOf(
                    Resource.Loading,
                    Resource.Success(artworkModelTemplate)
                ),
                actual = emits
            )
        }

        @Test
        fun `with error properly propagates the error`() = runTest {
            val error = NetworkError()
            coEvery { artworkService.getArtwork(any()) } throws error

            val emits =
                mutableListOf<Resource<Artwork>>()
            artworkRepository(testScheduler).getArtwork(123).toList(emits)

            assertEquals(
                expected = listOf(
                    Resource.Loading,
                    Resource.Failure(error),
                ) as List<Resource<Artwork>>,
                actual = emits
            )
        }
    }
}