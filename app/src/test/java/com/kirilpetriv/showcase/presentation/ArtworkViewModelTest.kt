package com.kirilpetriv.showcase.presentation

import com.kirilpetriv.showcase.core.ArtworkRepository
import com.kirilpetriv.showcase.models.NetworkError
import com.kirilpetriv.showcase.models.Resource
import com.kirilpetriv.showcase.templates.artworkModelTemplate
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class ArtworkDetailViewModelTest {
    private val artworkRepository: ArtworkRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private fun artworkViewModel(id: Long) = ArtworksDetailViewModel(
        repository = artworkRepository,
        id = id
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
        every { artworkRepository.getArtwork(any()) } returns flowOf(
            Resource.Success(artworkModelTemplate)
        )

        val states = mutableListOf<ArtworkDetailScreenState>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            artworkViewModel(123).state.toList(states)
        }
        advanceUntilIdle()
        assertEquals(
            expected = listOf(
                ArtworkDetailScreenState.Loading,
                ArtworkDetailScreenState.Success(artworkModelTemplate)
            ),
            actual = states
        )
    }

    @Test
    fun `with error returns error screen state`() = runTest {
        every { artworkRepository.getArtwork(any()) } returns flowOf(
            Resource.Failure(NetworkError(message = "Network Error"))
        )

        val states = mutableListOf<ArtworkDetailScreenState>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            artworkViewModel(123).state.toList(states)
        }
        advanceUntilIdle()

        assertEquals(
            expected = listOf(
                ArtworkDetailScreenState.Loading,
                ArtworkDetailScreenState.Error("Network Error")
            ),
            actual = states
        )
    }
}