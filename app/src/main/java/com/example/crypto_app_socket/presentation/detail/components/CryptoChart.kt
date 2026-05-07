package com.example.crypto_app_socket.presentation.detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.crypto_app_socket.core.BaseTheme
import com.example.crypto_app_socket.presentation.detail.model.ChartPointModel
import com.example.crypto_app_socket.ui.theme.LocalColors

@Composable
fun CryptoChart(
    points: List<ChartPointModel>,
    modifier: Modifier = Modifier
) {
    val colors = LocalColors.current
    val lineColor = colors.graphLineColor

    val progress by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 600),
        label = "chart_anim"
    )

    Canvas(modifier = modifier.fillMaxSize()) {

        if (points.size < 2) return@Canvas

        val minPrice = points.minOf { it.y }
        val maxPrice = points.maxOf { it.y }
        val priceRange = maxPrice - minPrice

        val circleRadius = BaseTheme.dimens.dp02.toPx()
        val endPadding = BaseTheme.dimens.dp05.toPx()

        val canvasWidth = size.width - endPadding

        val betweenSpaceX = canvasWidth / (points.size - 1)

        val topPadding = size.height * 0.1f
        val bottomPadding = size.height * 0.1f
        val canvasHeight = size.height - topPadding - bottomPadding

        val strokePath = Path()
        val fillPath = Path()

        var previousX = 0f
        var previousY = 0f

        points.forEachIndexed { index, point ->

            val x = index * betweenSpaceX * progress

            val yFraction =
                if (priceRange != 0f) (point.y - minPrice) / priceRange else 0.5f

            val y = size.height - bottomPadding - (yFraction * canvasHeight)

            if (index == 0) {
                strokePath.moveTo(x, y)

                fillPath.moveTo(x, size.height)
                fillPath.lineTo(x, y)
            } else {
                val controlX = (previousX + x) / 2

                strokePath.cubicTo(
                    controlX, previousY,
                    controlX, y,
                    x, y
                )

                fillPath.cubicTo(
                    controlX, previousY,
                    controlX, y,
                    x, y
                )
            }

            previousX = x
            previousY = y

            if (index == points.size - 1) {

                fillPath.lineTo(x, size.height)
                fillPath.close()

                drawCircle(
                    color = lineColor,
                    radius = circleRadius,
                    center = Offset(x, y)
                )
            }
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    lineColor.copy(alpha = 0.3f),
                    Color.Transparent
                ),
                endY = size.height
            )
        )

        drawPath(
            path = strokePath,
            color = lineColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}