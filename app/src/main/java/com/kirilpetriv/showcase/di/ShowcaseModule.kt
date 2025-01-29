package com.kirilpetriv.showcase.di

import org.koin.dsl.module

// Dependency injection handling
val showcaseModule = module {
    single { Service.provide() }
}