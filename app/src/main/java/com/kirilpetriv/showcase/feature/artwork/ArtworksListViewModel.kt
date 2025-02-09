package com.kirilpetriv.showcase.feature.artwork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kirilpetriv.domain.repository.ArtworkRepository

class ArtworksListViewModel(
    repository: com.kirilpetriv.domain.repository.ArtworkRepository
) : ViewModel() {
    val artworks = repository.getPaged().cachedIn(viewModelScope)
}