package com.kirilpetriv.artwork.di

import com.kirilpetriv.artwork.viewmodel.ArtworksDetailViewModel
import com.kirilpetriv.artwork.viewmodel.ArtworksListViewModel
import com.kirilpetriv.data.di.dataModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val artworkModule = module {
    includes(dataModule)
    viewModel { ArtworksListViewModel(repository = get()) }
    viewModel { (artworkId: Long) ->
        ArtworksDetailViewModel(
            repository = get(),
            id = artworkId
        )
    }
}