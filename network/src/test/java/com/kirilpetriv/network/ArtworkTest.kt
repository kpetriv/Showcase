package com.kirilpetriv.network

import com.kirilpetriv.network.dto.ArtworkDto
import com.kirilpetriv.network.dto.Result
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
    fun `with valid JSON returns deserialized Artwork`() {
        val json = """
{
    "data": {
        "id": 15854,
        "api_model": "artworks",
        "api_link": "https://api.artic.edu/api/v1/artworks/15854",
        "is_boosted": false,
        "title": "Seated Female Nude",
        "alt_titles": null,
        "thumbnail": {
            "lqip": "data:image/gif;base64,R0lGODlhBAAFAPQAAN3WyObf0uri1uzl2O7n3PLp2vbt4fXt4vjv4vzz5f305/ny6Pvz6Pvz6f316Pz16v316v737f/47gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAAAAAAALAAAAAAEAAUAAAURIHQYSkQEyCMARTMsDvNISQgAOw==",
            "width": 2724,
            "height": 3621,
            "alt_text": "A work made of graphite on ivory laid paper."
        },
        "main_reference_number": "1933.908",
        "has_not_been_viewed_much": false,
        "boost_rank": null,
        "date_start": 1925,
        "date_end": 1925,
        "date_display": "c. 1925",
        "date_qualifier_title": "c.",
        "date_qualifier_id": 60,
        "artist_display": "André Lhote\nFrench, 1885-1962",
        "place_of_origin": "France",
        "description": null,
        "short_description": null,
        "dimensions": "44.3 × 33.3 cm (17 1/2 × 13 1/8 in.)",
        "dimensions_detail": [
            {
                "depth": null,
                "width": 33,
                "height": 44,
                "diameter": null,
                "clarification": null
            }
        ],
        "medium_display": "Graphite on ivory laid paper",
        "inscriptions": "Signed recto, lower right, in pen and black ink: \"A. Lhote\"",
        "credit_line": "Mr. and Mrs. Carter H. Harrison Collection",
        "catalogue_display": null,
        "publication_history": null,
        "exhibition_history": "Musée des beaux-arts de Montréal, \"Berthe Weill: Art Dealer of the Parisian Avant-garde\", May 5 - September 7, 2025, traveled to New York City, Grey Art Gallery & Study Center, October 1, 2024 - March 1, 2025, cat. by Stephane Aquin, et. al.",
        "provenance_text": "Sold by the artist to Carter H. Harrison (1860-1953), Chicago, by 1934 [Harrison collection notes dated October 18, 1934 in curatorial file]; given to the Art Institute, 1933.",
        "edition": null,
        "publishing_verification_level": "Web Cataloged",
        "internal_department_id": 3,
        "fiscal_year": 1933,
        "fiscal_year_deaccession": null,
        "is_public_domain": false,
        "is_zoomable": true,
        "max_zoom_window_size": 1280,
        "copyright_notice": "© 2018 Artists Rights Society (ARS), New York / ADAGP, Paris",
        "has_multimedia_resources": false,
        "has_educational_resources": false,
        "has_advanced_imaging": false,
        "colorfulness": 11.7465,
        "color": {
            "h": 38,
            "l": 69,
            "s": 58,
            "percentage": 0.0007394191756743767,
            "population": 7
        },
        "latitude": null,
        "longitude": null,
        "latlon": null,
        "is_on_view": false,
        "on_loan_display": "<p>On loan to Grey Art Gallery & Study Center in New York City for <i>Berthe Weill: Art Dealer of the Parisian Avant-garde</i></p>",
        "gallery_title": null,
        "gallery_id": null,
        "nomisma_id": null,
        "artwork_type_title": "Drawing and Watercolor",
        "artwork_type_id": 14,
        "department_title": "Prints and Drawings",
        "department_id": "PC-13",
        "artist_id": 35474,
        "artist_title": "André Lhote",
        "alt_artist_ids": [],
        "artist_ids": [
            35474
        ],
        "artist_titles": [
            "André Lhote"
        ],
        "category_ids": [
            "PC-13"
        ],
        "category_titles": [
            "Prints and Drawings"
        ],
        "term_titles": [
            "graphite",
            "paper (fiber product)",
            "graphite",
            "drawings (visual works)",
            "prints and drawing"
        ],
        "style_id": null,
        "style_title": null,
        "alt_style_ids": [],
        "style_ids": [],
        "style_titles": [],
        "classification_id": "TM-175",
        "classification_title": "graphite",
        "alt_classification_ids": [
            "TM-5",
            "TM-4"
        ],
        "classification_ids": [
            "TM-175",
            "TM-5",
            "TM-4"
        ],
        "classification_titles": [
            "graphite",
            "drawings (visual works)",
            "prints and drawing"
        ],
        "subject_id": null,
        "alt_subject_ids": [],
        "subject_ids": [],
        "subject_titles": [],
        "material_id": "TM-2982",
        "alt_material_ids": [
            "TM-2410"
        ],
        "material_ids": [
            "TM-2982",
            "TM-2410"
        ],
        "material_titles": [
            "paper (fiber product)",
            "graphite"
        ],
        "technique_id": null,
        "alt_technique_ids": [],
        "technique_ids": [],
        "technique_titles": [],
        "theme_titles": [],
        "image_id": "98d02161-dba8-6150-ba19-6bc1c1c6f897",
        "alt_image_ids": [],
        "document_ids": [],
        "sound_ids": [],
        "video_ids": [],
        "text_ids": [],
        "section_ids": [],
        "section_titles": [],
        "site_ids": [],
        "suggest_autocomplete_all": [
            {
                "input": [
                    "1933.908"
                ],
                "contexts": {
                    "groupings": [
                        "accession"
                    ]
                }
            },
            {
                "input": [
                    "Seated Female Nude"
                ],
                "weight": 416,
                "contexts": {
                    "groupings": [
                        "title"
                    ]
                }
            }
        ],
        "source_updated_at": "2025-02-08T00:30:01-06:00",
        "updated_at": "2025-02-08T00:31:15-06:00",
        "timestamp": "2025-02-08T11:10:16-06:00"
    },
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
            expected = Result(
                data = ArtworkDto(
                    id = 15854,
                    title = "Seated Female Nude",
                    artist = "André Lhote\nFrench, 1885-1962",
                    description = null,
                    imageUuid = "98d02161-dba8-6150-ba19-6bc1c1c6f897",
                )
            ),
            actual = jsonConfig.decodeFromString(json)
        )
    }
}

