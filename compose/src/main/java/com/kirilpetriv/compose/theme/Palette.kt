package com.kirilpetriv.compose.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// region Palettes

internal val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

internal val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


// endregion

// region Extension Palettes
val Color.Alpha10: Color get() = copy(alpha = 0.1F)
val Color.Alpha20: Color get() = copy(alpha = 0.2F)
val Color.Alpha25: Color get() = copy(alpha = 0.25F)
val Color.Alpha40: Color get() = copy(alpha = 0.4F)
val Color.Alpha60: Color get() = copy(alpha = 0.6F)
val Color.Alpha80: Color get() = copy(alpha = 0.8F)
// endregion