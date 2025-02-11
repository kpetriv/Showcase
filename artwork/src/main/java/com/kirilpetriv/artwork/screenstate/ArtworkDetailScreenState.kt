package com.kirilpetriv.artwork.screenstate

import com.kirilpetriv.model.Artwork

sealed interface ArtworkDetailScreenState {
    data object Loading : ArtworkDetailScreenState
    data class Success(val artwork: Artwork) : ArtworkDetailScreenState
    data class Error(val message: String?) : ArtworkDetailScreenState
}