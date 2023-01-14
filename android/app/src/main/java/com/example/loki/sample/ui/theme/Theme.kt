package com.example.loki.sample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
  primary = Color(0xFF000000),
  primaryVariant = Color(0xFF000000),
  secondary = Color(0xFF000000),
  onPrimary = Color(0xFFFFFFFF),
  onSecondary = Color(0xFFFFFFFF),
)

private val LightColorPalette = lightColors(
  primary = Color(0xFFFFFFFF),
  primaryVariant = Color(0xFFFFFFFF),
  secondary = Color(0xFFfafafa),
  onPrimary = Color(0xFF000000),
  onSecondary = Color(0xFF000000),

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
fun LokiSampleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}