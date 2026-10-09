package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RacingAmber,
    onPrimary = Color.Black,
    primaryContainer = RacingAmberDark,
    onPrimaryContainer = Color.White,
    secondary = TechCyan,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF004D61),
    onSecondaryContainer = Color(0xFFBCE9FF),
    tertiary = TitaniumSilver,
    onTertiary = Color.Black,
    background = CarbonBlack,
    onBackground = TitaniumSilver,
    surface = CharcoalDark,
    onSurface = TitaniumSilver,
    surfaceVariant = GunmetalSurface,
    onSurfaceVariant = MutedSlate,
    outline = GunmetalBorder,
    outlineVariant = DarkBorder,
    error = StatusOverdue,
    onError = Color.White
)

@Composable
fun MotoGuideTheme(
    darkTheme: Boolean = true, // Force modern dark motorsport theme
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}

// Backward compatibility alias for template tests
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MotoGuideTheme(darkTheme = true, content = content)
}
