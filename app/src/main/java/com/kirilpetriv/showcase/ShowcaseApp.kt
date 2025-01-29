package com.kirilpetriv.showcase

import android.app.Application
import com.kirilpetriv.showcase.di.showcaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ShowcaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ShowcaseApp)
            modules(showcaseModule)
        }
    }
}