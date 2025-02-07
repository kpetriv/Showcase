package com.kirilpetriv.showcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirilpetriv.showcase.core.ArtworkRepository
import com.kirilpetriv.showcase.models.Resource
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

    fun getArtworks() {
        viewModelScope.launch {
            repository.getArtworks().collect { result ->
                _state.update {
                    when (result) {
                        Resource.Loading -> ArtworkScreenState.Loading
                        is Resource.Failure -> ArtworkScreenState.Error(result.error.message)
                        is Resource.Success -> ArtworkScreenState.Success(result.value)

                    }
                }
            }
        }
    }
}