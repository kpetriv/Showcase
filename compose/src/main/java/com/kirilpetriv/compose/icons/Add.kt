package com.kirilpetriv.compose.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

private var cache: ImageVector? = null

internal val internalAdd: ImageVector
    get() {
        if (cache != null) {
            return cache!!
        }
        cache = ImageVector.Builder(
            name = "Add",
            defaultWidth = 24.dp,
            defaultHeight = 25.dp,
            viewportWidth = 24f,
            viewportHeight = 25f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(0f, 0f)
                horizontalLineTo(24f)
                verticalLineTo(24f)
                horizontalLineTo(0f)
                verticalLineTo(0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF888888)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(13f, 7.5f)
                horizontalLineTo(11f)
                verticalLineTo(11.5f)
                horizontalLineTo(7f)
                verticalLineTo(13.5f)
                horizontalLineTo(11f)
                verticalLineTo(17.5f)
                horizontalLineTo(13f)
                verticalLineTo(13.5f)
                horizontalLineTo(17f)
                verticalLineTo(11.5f)
                horizontalLineTo(13f)
                verticalLineTo(7.5f)
                close()
                moveTo(12f, 2.5f)
                curveTo(6.48f, 2.5f, 2f, 6.98f, 2f, 12.5f)
                curveTo(2f, 18.02f, 6.48f, 22.5f, 12f, 22.5f)
                curveTo(17.52f, 22.5f, 22f, 18.02f, 22f, 12.5f)
                curveTo(22f, 6.98f, 17.52f, 2.5f, 12f, 2.5f)
                close()
                moveTo(12f, 20.5f)
                curveTo(7.59f, 20.5f, 4f, 16.91f, 4f, 12.5f)
                curveTo(4f, 8.09f, 7.59f, 4.5f, 12f, 4.5f)
                curveTo(16.41f, 4.5f, 20f, 8.09f, 20f, 12.5f)
                curveTo(20f, 16.91f, 16.41f, 20.5f, 12f, 20.5f)
                close()
            }
        }.build()
        return cache!!
    }
