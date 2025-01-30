package com.kirilpetriv.showcase.core

import java.io.IOException

open class NetworkError(
    message: String? = null,
    throwable: Throwable? = null,
) : IOException(message, throwable)
