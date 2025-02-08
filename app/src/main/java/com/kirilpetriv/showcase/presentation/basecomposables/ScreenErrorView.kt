package com.kirilpetriv.showcase.presentation.basecomposables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kirilpetriv.showcase.R

@Composable
internal fun ScreenErrorView(
    icon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Back",
            tint = Color.Red,
            modifier = Modifier.size(32.dp)
        )
    },
    actions: @Composable (RowScope.() -> Unit)? = null,
    title: String = stringResource(R.string.artworks_error_title),
    subtitle: String = stringResource(R.string.artwork_error_subtitle),
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    ErrorView(
        icon = icon,
        title = title,
        subtitle = subtitle,
        color = Color.Red,
        modifier = modifier,
        actions = actions
    )
}