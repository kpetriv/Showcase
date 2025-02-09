package com.kirilpetriv.showcase.feature.artwork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirilpetriv.model.Resource
import com.kirilpetriv.domain.repository.ArtworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtworksDetailViewModel(
    private val id: Long,
    private val repository: ArtworkRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<ArtworkDetailScreenState>(ArtworkDetailScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        loadArtwork()
    }

    fun loadArtwork() {
        viewModelScope.launch {
            repository.getArtwork(id = id).collect { result ->
                _state.update {
                    when (result) {
                        Resource.Loading -> ArtworkDetailScreenState.Loading
                        is Resource.Failure -> ArtworkDetailScreenState.Error(result.error.message)
                        is Resource.Success -> ArtworkDetailScreenState.Success(result.value)
                    }
                }
            }
        }
    }
}