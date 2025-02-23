package com.kirilpetriv.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

private val LocalDevice = staticCompositionLocalOf { Device.Mobile }
private val LocalOrientation = staticCompositionLocalOf { Orientation.Portrait }

/**
 * Device type
 */
internal enum class Device {
    Mobile,
    Tablet,
}

/**
 * Orientation of [Device]
 */
internal enum class Orientation {
    Portrait,
    Landscape,
}

val isTablet: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalDevice.current == Device.Tablet

val isLandscape: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalOrientation.current == Orientation.Landscape

/**
 * This function is used to set the current values of [LocalDevice] and [LocalOrientation].
 * It is set implicitly using the [LocalConfiguration] screen width and height
 *
 * @see LocalDevice
 * @see LocalOrientation
 */
@Composable
internal fun ProvideDevice(content: @Composable () -> Unit) {
    val configuration = LocalConfiguration.current

    val deviceWidth = minOf(configuration.screenWidthDp, configuration.screenHeightDp)
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    val device = if (deviceWidth >= 600) Device.Tablet else Device.Mobile
    val orientation = if (isLandscape) Orientation.Landscape else Orientation.Portrait

    CompositionLocalProvider(
        LocalDevice provides device,
        LocalOrientation provides orientation,
        content = content
    )
}