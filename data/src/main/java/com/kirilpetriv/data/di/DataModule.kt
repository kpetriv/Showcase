package com.kirilpetriv.data.di

import com.kirilpetriv.data.repository.ArtworkRepositoryImpl
import com.kirilpetriv.domain.repository.ArtworkRepository
import com.kirilpetriv.network.di.networkModule
import org.koin.dsl.module

val dataModule = module {
    includes(networkModule)
    single<ArtworkRepository> { ArtworkRepositoryImpl(get()) }
}