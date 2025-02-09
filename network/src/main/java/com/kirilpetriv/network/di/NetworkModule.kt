package com.kirilpetriv.network.di

import com.kirilpetriv.network.service.ArtworkService
import org.koin.dsl.module

val networkModule = module {
    single { ArtworkService.provide() }
}