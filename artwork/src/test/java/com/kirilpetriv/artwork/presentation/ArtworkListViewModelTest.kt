package com.kirilpetriv.artwork.presentation

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.kirilpetriv.domain.repository.ArtworkRepository
import com.kirilpetriv.artwork.templates.artworkModelTemplate
import com.kirilpetriv.artwork.viewmodel.ArtworksListViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ArtworkListViewModelTest {
    private val artworkRepository: ArtworkRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private fun artworkViewModel() = ArtworksListViewModel(
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
    fun `returns expected paging data`() = runTest {
        val expectedData = listOf(artworkModelTemplate)
        val pagingData = PagingData.from(expectedData)

        coEvery { artworkRepository.getPaged() } returns flowOf(pagingData)

        // asSnapshot() doesn't collect the data when cachedId() is used. Injecting scope to the vm
        // or other coroutine handling does not work.
        val resultList = artworkViewModel().artworks.first().let {
            flowOf(it)
        }.asSnapshot()

        assertEquals(expectedData, resultList)
    }
}