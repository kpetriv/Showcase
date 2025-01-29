package com.kirilpetriv.showcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirilpetriv.showcase.core.ArtworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtworksViewModel(
    private val repository: ArtworkRepository
) : ViewModel() {
    private val _state: MutableStateFlow<ArtworkScreenState> =
        MutableStateFlow(ArtworkScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        getArtworks()
    }

    private fun getArtworks() {
        viewModelScope.launch {
            runCatching {
                repository.getArtworks()
            }.onSuccess { countries ->
                _state.update { ArtworkScreenState.Success(countries) }
            }.onFailure { error ->
                _state.update { ArtworkScreenState.Error(error.message ?: "Unknown error") }
            }
        }
    }
}