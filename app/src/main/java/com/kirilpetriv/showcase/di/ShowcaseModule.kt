package com.kirilpetriv.showcase.di

import com.kirilpetriv.network.di.networkModule
import com.kirilpetriv.showcase.core.ArtworkRepository
import com.kirilpetriv.showcase.data.ArtworkRepositoryImpl
import com.kirilpetriv.showcase.feature.artwork.ArtworksDetailViewModel
import com.kirilpetriv.showcase.feature.artwork.ArtworksListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val showcaseModule = module {
    includes(networkModule)
    single<ArtworkRepository> { ArtworkRepositoryImpl(get()) }
    viewModel { ArtworksListViewModel(repository = get()) }
    viewModel { (artworkId: Long) -> ArtworksDetailViewModel(repository = get(), id = artworkId) }
}