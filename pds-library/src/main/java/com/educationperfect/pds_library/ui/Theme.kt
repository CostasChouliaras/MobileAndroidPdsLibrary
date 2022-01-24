package com.educationperfect.pds_library.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Midnight500,
    primaryVariant = Blue500,
    onPrimary = Neutral100,
    secondary = Pink500,
    secondaryVariant = Pink500,
    onSecondary = Neutral100,
    background = Color.White,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.White,
    error = Red500,
    onError = Neutral100
)

private val LightColorPalette = lightColors(
    primary = Midnight500,
    primaryVariant = Blue500,
    onPrimary = Neutral100,
    secondary = Pink500,
    secondaryVariant = Pink500,
    onSecondary = Neutral100,
    background = Color.White,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.White,
    error = Red500,
    onError = Neutral100
)

@Composable
fun PdsLibraryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = EdsTypography,
        shapes = Shapes,
        content = content
    )
}