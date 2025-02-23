package com.kirilpetriv.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val LocalDimensions = staticCompositionLocalOf { mobileDimensions }

/**
 * Retrieves the current [Dimensions] at the call site's position in the hierarchy.
 */
@Suppress("unused")
val MaterialTheme.dimens: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current

/**
 * Dimensions set. The dimensions used for the theme provided by shirt size dimensions
 */
data class Dimensions(
    val xs: Dp,
    val s: Dp,
    val m: Dp,
    val l: Dp,
    val xl: Dp,
    val xxl: Dp,
    val xxxl: Dp,
    val xxxxl: Dp,
)

/**
 * This function is used to set the current value of [LocalDimensions].
 * @param isTablet the device to distinguish the dimensions for.
 * @see LocalDimensions
 */
@Composable
internal fun ProvideDimensions(isTablet: Boolean, content: @Composable () -> Unit) {
    val dimensions = if (isTablet) tabletDimensions else mobileDimensions
    CompositionLocalProvider(LocalDimensions provides dimensions, content = content)
}

/**
 * Dimensions set for mobile devices.
 */
private val mobileDimensions
    get() = Dimensions(
        xs = 8.dp,
        s = 16.dp,
        m = 24.dp,
        l = 32.dp,
        xl = 40.dp,
        xxl = 48.dp,
        xxxl = 62.dp,
        xxxxl = 80.dp,
    )

/**
 * Dimensions set for tablet devices.
 */
private val tabletDimensions
    get() = Dimensions(
        xs = 16.dp,
        s = 32.dp,
        m = 48.dp,
        l = 64.dp,
        xl = 80.dp,
        xxl = 96.dp,
        xxxl = 128.dp,
        xxxxl = 160.dp,
    )