package com.kirilpetriv.showcase.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.artic.edu/api/v1/"

interface ArtworkService {

    @GET("artworks")
    suspend fun getArtworks(
        @Query("fields") fields: String = "id,title,artist_display,image_id,description",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
    ): Page<ArtworkDto>

    companion object {
        fun provide(): ArtworkService {
            val jsonConfig = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                encodeDefaults = true
            }
            val jsonContentType: MediaType = "application/json".toMediaType()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(jsonConfig.asConverterFactory(contentType = jsonContentType))
                .build()

            return retrofit.create(ArtworkService::class.java)
        }
    }
}