package com.example.crypto_app_socket.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColors = compositionLocalOf<AppColors> {
    error("No colors provided")
}

data class AppColors(
    val background : Color,
    val onBackgroundText : Color,
    val onBackgroundDown : Color,
    val onBackgroundUp : Color,
    val graphColor : Color,
    val stableColor : Color,
    val graphLineColor : Color
)

val darkModeColor = AppColors(
    background = darkBackground,
    onBackgroundText = darkTextColor,
    onBackgroundUp = upColor,
    onBackgroundDown = downColor,
    graphColor = graphColor,
    stableColor = stableColor,
    graphLineColor = graphLineColor

)