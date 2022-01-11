package com.educationperfect.androidpdslibrary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.educationperfect.pds_library.ui.EdsTypography
import com.educationperfect.pds_library.ui.Shapes

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PdsLibraryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        com.educationperfect.pds_library.ui.DarkColorPalette
    } else {
        com.educationperfect.pds_library.ui.LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = EdsTypography,
        shapes = Shapes,
        content = content
    )
}