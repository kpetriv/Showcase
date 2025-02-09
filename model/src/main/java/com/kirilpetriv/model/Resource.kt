package com.kirilpetriv.model

sealed class Resource<out T> {
    data class Success<T>(val value: T) : Resource<T>()
    data class Failure(val error: Throwable) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}