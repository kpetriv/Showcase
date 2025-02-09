package com.kirilpetriv.showcase.feature.artwork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kirilpetriv.showcase.core.ArtworkRepository

class ArtworksListViewModel(
    repository: ArtworkRepository
) : ViewModel() {
    val artworks = repository.getPaged().cachedIn(viewModelScope)
}