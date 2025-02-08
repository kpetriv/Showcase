package com.kirilpetriv.showcase.di

import com.kirilpetriv.showcase.network.ArtworkService
import com.kirilpetriv.showcase.core.ArtworkRepository
import com.kirilpetriv.showcase.data.ArtworkRepositoryImpl
import com.kirilpetriv.showcase.presentation.ArtworksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val showcaseModule = module {
    single { ArtworkService.provide() }
    single<ArtworkRepository> { ArtworkRepositoryImpl(get()) }
    viewModel { ArtworksViewModel(repository = get()) }
}