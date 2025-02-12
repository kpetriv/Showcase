package com.kirilpetriv.logger

import timber.log.Timber

object Logger {
    enum class Type {
        DEBUG,
        TEST,
        STAGE,
        RELEASE
    }

    fun setup(type: Type) {
        when (type) {
            Type.DEBUG   -> Timber.plant(Timber.DebugTree())
            Type.TEST    -> Timber.plant(Timber.DebugTree(), CrashlyticsTree())
            Type.STAGE   -> Timber.plant(Timber.DebugTree(), CrashlyticsTree())
            Type.RELEASE -> Timber.plant(CrashlyticsTree())
        }
    }

    fun d(message: String) = Timber.d(message = message)
    fun i(message: String) = Timber.i(message = message)
    fun w(message: String, throwable: Throwable? = null) = Timber.w(message = message, t = throwable)
    fun e(message: String, throwable: Throwable) = Timber.e(message = message, t = throwable)
}