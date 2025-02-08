package com.kirilpetriv.showcase.presentation

import com.kirilpetriv.showcase.models.Artwork

sealed interface ArtworkDetailScreenState {
    data object Loading : ArtworkDetailScreenState
    data class Success(val artwork: Artwork) : ArtworkDetailScreenState
    data class Error(val message: String?) : ArtworkDetailScreenState
}