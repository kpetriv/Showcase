package com.kirilpetriv.showcase.feature.artwork

import android.text.Html
import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import com.kirilpetriv.model.Artwork

// Simplified version of the helper methods

internal fun com.kirilpetriv.model.Artwork.getMainImageUrl(): String {
    return "https://www.artic.edu/iiif/2/${imageUuid}/full/843,/0/default.jpg"
}

internal fun com.kirilpetriv.model.Artwork.getThumbnailImageUrl(): String {
    return "https://www.artic.edu/iiif/2/${imageUuid}/full/100,/0/default.jpg"
}

internal fun getFormattedDescription(string: String): AnnotatedString {
    val spanned: Spanned = Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT)
    val annotatedString = buildAnnotatedString {
        append(spanned.toString())
    }
    return annotatedString
}