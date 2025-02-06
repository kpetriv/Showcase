package com.kirilpetriv.showcase.presentation

import com.kirilpetriv.showcase.core.ArtworkRepository
import com.kirilpetriv.showcase.core.NetworkError
import com.kirilpetriv.showcase.templates.artworkModelTemplate
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ArtworkViewModelTest {
    private val artworkRepository: ArtworkRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private fun artworkViewModel() = ArtworksViewModel(
        repository = artworkRepository,
    )

    @BeforeEach
    fun init() {
        clearAllMocks()
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `with successful result returns result state`() = runTest {
        coEvery { artworkRepository.getArtworks() } returns listOf(artworkModelTemplate)

        val states = mutableListOf<ArtworkScreenState>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            artworkViewModel().state.toList(states)
        }
        advanceUntilIdle()
        assertEquals(
            expected = listOf(
                ArtworkScreenState.Loading,
                ArtworkScreenState.Success(listOf(artworkModelTemplate))
            ),
            actual = states
        )
    }

    @Test
    fun `with error returns error screen state`() = runTest {
        coEvery { artworkRepository.getArtworks() } throws NetworkError(message = "Network Error")

        val states = mutableListOf<ArtworkScreenState>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            artworkViewModel().state.toList(states)
        }
        advanceUntilIdle()

        assertEquals(
            expected = listOf(
                ArtworkScreenState.Loading,
                ArtworkScreenState.Error("Network Error")
            ),
            actual = states
        )
    }
}