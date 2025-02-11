package com.kirilpetriv.artwork.presentation

import com.kirilpetriv.artwork.templates.artworkModelTemplate
import com.kirilpetriv.artwork.util.getMainImageUrl
import com.kirilpetriv.artwork.util.getThumbnailImageUrl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArtworkExtTest {

    @Test
    fun `main image url`() {
        assertEquals(
            expected = "https://www.artic.edu/iiif/2/${artworkModelTemplate.imageUuid}/full/843,/0/default.jpg",
            actual = artworkModelTemplate.getMainImageUrl()
        )
    }

    @Test
    fun `thumbnail image url`() {
        // This test emphasises proper value transformation, that's why they have to be specific
        assertEquals(
            expected = "https://www.artic.edu/iiif/2/${artworkModelTemplate.imageUuid}/full/100,/0/default.jpg",
            actual = artworkModelTemplate.getThumbnailImageUrl()
        )
    }
}