package com.kirilpetriv.showcase.core

interface ArtworkRepository {
    /**
     * @return a list of [Artwork] objects.
     * @throws Exception if an error occurs while fetching the data.
     */
    suspend fun getArtworks(): List<Artwork>
}