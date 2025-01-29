package com.kirilpetriv.showcase.presentation

import com.kirilpetriv.showcase.core.Artwork

sealed interface ArtworkScreenState {
    data object Loading : ArtworkScreenState
    data class Success(val artworks: List<Artwork>) : ArtworkScreenState
    data class Error(val message: String) : ArtworkScreenState
}