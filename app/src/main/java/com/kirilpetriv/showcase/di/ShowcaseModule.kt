package com.kirilpetriv.showcase.di

import ArtworkService
import com.kirilpetriv.showcase.network.ArtworkRepositoryImpl
import org.koin.dsl.module

val showcaseModule = module {
    single { ArtworkService.provide() }
    single { ArtworkRepositoryImpl(get()) }
}