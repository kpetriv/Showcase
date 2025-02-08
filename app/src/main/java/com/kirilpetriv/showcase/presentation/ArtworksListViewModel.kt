package com.kirilpetriv.showcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kirilpetriv.showcase.core.ArtworkRepository

class ArtworksListViewModel(
    private val repository: ArtworkRepository
) : ViewModel() {
    val artworks = repository.getPaged().cachedIn(viewModelScope)
}