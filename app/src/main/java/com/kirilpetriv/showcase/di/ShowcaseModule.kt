package com.kirilpetriv.showcase.di

import com.kirilpetriv.data.di.dataModule
import com.kirilpetriv.showcase.feature.artwork.ArtworksDetailViewModel
import com.kirilpetriv.showcase.feature.artwork.ArtworksListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val showcaseModule = module {
    includes(dataModule)
    viewModel { ArtworksListViewModel(repository = get()) }
    viewModel { (artworkId: Long) -> ArtworksDetailViewModel(repository = get(), id = artworkId) }
}