package com.kirilpetriv.compose.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

val TextStyle.bold: TextStyle
    get() = this.copy(fontWeight = FontWeight.Bold)

val TextStyle.light: TextStyle
    get() = this.copy(fontWeight = FontWeight.Light)