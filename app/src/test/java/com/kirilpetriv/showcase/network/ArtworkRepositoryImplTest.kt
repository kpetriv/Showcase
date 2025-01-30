package com.kirilpetriv.showcase.network

import com.kirilpetriv.showcase.core.NetworkError
import com.kirilpetriv.showcase.templates.artworkDtoTemplate
import com.kirilpetriv.showcase.templates.artworkModelTemplate
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

        val result = artworkRepository(testScheduler).getArtworks()

        assertEquals(
            expected = listOf(artworkModelTemplate),
            actual = result
        )
    }

    @Test
    fun `with error properly propagates the error`() = runTest {
        coEvery { artworkService.getArtworks() } throws NetworkError()

        assertThrows<NetworkError> {
            artworkRepository(testScheduler).getArtworks()
        }
    }
}