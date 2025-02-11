package com.kirilpetriv.showcase.di

import com.kirilpetriv.artwork.di.artworkModule
import org.koin.dsl.module

val showcaseModule = module {
    includes(artworkModule)
}