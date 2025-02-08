package com.kirilpetriv.showcase.presentation

import com.kirilpetriv.showcase.models.Artwork

sealed interface ArtworkListScreenState {
    data object Loading : ArtworkListScreenState
    data class Success(val artworks: List<Artwork>) : ArtworkListScreenState
    data class Error(val message: String?) : ArtworkListScreenState
}