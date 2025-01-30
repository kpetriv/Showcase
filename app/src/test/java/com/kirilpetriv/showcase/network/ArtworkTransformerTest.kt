package com.kirilpetriv.showcase.network

import com.kirilpetriv.showcase.templates.artworkDtoTemplate
import com.kirilpetriv.showcase.templates.artworkModelTemplate
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArtworkTransformerTest {
    @Test
    fun `artwork dto to model`() {
        // This test emphasises proper value transformation, that's why they have to be specific
        assertEquals(
            expected = artworkModelTemplate.copy(
                id = 321,
                title = "random title",
                imageUuid = "6c046e7f-614b-0060-2e7b-e12779ae48b6",
                artist = "random artist",
                description = "random description"
            ),
            actual = artworkDtoTemplate.copy(
                id = 321,
                title = "random title",
                imageUuid = "6c046e7f-614b-0060-2e7b-e12779ae48b6",
                artist = "random artist",
                description = "random description"
            ).toModel()
        )
    }
}