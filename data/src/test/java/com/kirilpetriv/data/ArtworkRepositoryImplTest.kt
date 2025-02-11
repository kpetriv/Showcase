package com.kirilpetriv.data

import androidx.paging.testing.asSnapshot
import com.kirilpetriv.data.repository.ArtworkRepositoryImpl
import com.kirilpetriv.data.templates.artworkDtoTemplate
import com.kirilpetriv.data.templates.artworkModelTemplate
import com.kirilpetriv.data.transformer.toModel
import com.kirilpetriv.model.Artwork
import com.kirilpetriv.model.NetworkError
import com.kirilpetriv.model.Resource
import com.kirilpetriv.network.dto.Page
import com.kirilpetriv.network.dto.Result
import com.kirilpetriv.network.service.ArtworkService
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
import kotlin.test.assertFailsWith

class ArtworkRepositoryImplTest {

    private val artworkService: ArtworkService = mockk()

    private fun artworkRepository(scheduler: TestCoroutineScheduler) =
        ArtworkRepositoryImpl(
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

            val emits = mutableListOf<Resource<Artwork>>()
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

            val emits = mutableListOf<Resource<Artwork>>()
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

    @Nested
    inner class GetPaged {
        @Test
        fun `with successful request properly returns a flow of paging data`() = runTest {
            val artworkList1 = (1..20).map { artworkDtoTemplate.copy(id = it.toLong()) }
            val artworkList2 = (21..40).map { artworkDtoTemplate.copy(id = it.toLong()) }
            coEvery {
                artworkService.getArtworks(fields = any(), page = 1, limit = any())
            } returns Page(
                data = artworkList1,
            )
            coEvery {
                artworkService.getArtworks(fields = any(), page = 2, limit = any())
            } returns Page(
                data = artworkList2,
            )

            val resultList = artworkRepository(testScheduler).getPaged().asSnapshot()

            assertEquals(
                expected = (artworkList1 + artworkList2).map { it.toModel() },
                actual = resultList
            )
        }

        @Test
        fun `with error properly propagates the error`() = runTest {
            val error = NetworkError()
            coEvery {
                artworkService.getArtworks(fields = any(), page = 1, limit = any())
            } throws error

            assertFailsWith<NetworkError> {
                artworkRepository(testScheduler).getPaged().asSnapshot()
            }
        }
    }
}