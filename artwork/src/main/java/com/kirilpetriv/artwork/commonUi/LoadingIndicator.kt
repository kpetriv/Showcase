package com.kirilpetriv.artwork.commonUi

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    strokeCap: StrokeCap = StrokeCap.Square,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    CircularProgressIndicator(
        color = color,
        modifier = modifier,
        strokeCap = strokeCap,
        strokeWidth = strokeWidth,
    )
}

@Composable
fun LoadingIndicatorScreen(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        LoadingIndicator(
            color = color,
        )
    }
}