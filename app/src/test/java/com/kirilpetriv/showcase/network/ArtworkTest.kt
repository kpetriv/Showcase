package com.kirilpetriv.showcase.network

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArtworkTest {

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
    }

    @Test
    fun `with valid JSON returns deserialized Artwork list`() {
        val json = """
{
    "pagination": {
        "total": 127425,
        "limit": 1,
        "offset": 0,
        "total_pages": 127425,
        "current_page": 1,
        "next_url": "https://api.artic.edu/api/v1/artworks?page=2&fields=id%2Ctitle%2Cartist_display%2Cimage_id%2Cdescription&limit=1"
    },
    "data": [
        {
            "id": 156620,
            "title": "Three Samurai",
            "artist_display": "Hamada Taisuke\nJapanese, born 1932",
            "description": null,
            "image_id": "0b72c593-001d-d268-4ff2-05a9ca347b7a"
        }
    ],
    "info": {
        "license_text": "The `description` field in this response is licensed under a Creative Commons Attribution 4.0 Generic License (CC-By) and the Terms and Conditions of artic.edu. All other data in this response is licensed under a Creative Commons Zero (CC0) 1.0 designation and the Terms and Conditions of artic.edu.",
        "license_links": [
            "https://creativecommons.org/publicdomain/zero/1.0/",
            "https://www.artic.edu/terms"
        ],
        "version": "1.13"
    },
    "config": {
        "iiif_url": "https://www.artic.edu/iiif/2",
        "website_url": "http://www.artic.edu"
    }
}
            """.trimIndent()
        assertEquals(
            expected = Page<Artwork>(
                data = listOf(
                    Artwork(
                        id = 156620,
                        title = "Three Samurai",
                        artist = "Hamada Taisuke\nJapanese, born 1932",
                        description = null,
                        imageUuid = "0b72c593-001d-d268-4ff2-05a9ca347b7a"
                    )
                )
            ),
            actual = jsonConfig.decodeFromString(json)
        )
    }
}

