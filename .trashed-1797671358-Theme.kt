package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class UpsellExtendedColors(
    val obsidianBackground: Color = ObsidianBlack,
    val cardSurface: Color = CharcoalSurface,
    val elevatedCardSurface: Color = ElevatedCharcoal,
    val goldPrimary: Color = LuxuryGold,
    val goldHighlight: Color = ChampagneGold,
    val goldMuted: Color = DeepBronzeGold,
    val goldSoftTint: Color = SoftGoldTint,
    val goldBorder: Color = SubtleGoldBorder,
    val slateBorder: Color = SubtleSlateBorder,
    val textPrimary: Color = CrispWhite,
    val textSecondary: Color = MutedSilver,
    val textMuted: Color = DimSilver,
    val urgencyRed: Color = CrimsonUrgency,
    val urgencyRedBright: Color = CrimsonUrgencyBright,
    val urgencyRedDark: Color = CrimsonUrgencyDark,
    val successGreen: Color = EmeraldSuccess,
)

val LocalUpsellColors = staticCompositionLocalOf { UpsellExtendedColors() }

private val LuxuryDarkColorScheme = darkColorScheme(
    primary = LuxuryGold,
    onPrimary = ObsidianBlack,
    primaryContainer = SoftGoldTint,
    onPrimaryContainer = ChampagneGold,
    secondary = ChampagneGold,
    onSecondary = ObsidianBlack,
    secondaryContainer = ElevatedCharcoal,
    onSecondaryContainer = CrispWhite,
    tertiary = CrimsonUrgency,
    onTertiary = CrispWhite,
    background = ObsidianBlack,
    onBackground = CrispWhite,
    surface = CharcoalSurface,
    onSurface = CrispWhite,
    surfaceVariant = ElevatedCharcoal,
    onSurfaceVariant = MutedSilver,
    outline = SubtleGoldBorder,
    outlineVariant = SubtleSlateBorder,
    error = CrimsonUrgencyBright,
    onError = CrispWhite,
)

private val LuxuryShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(24.dp),
)

@Composable
fun Metodo15MinTheme(
    content: @Composable () -> Unit,
) {
    val extendedColors = UpsellExtendedColors()
    CompositionLocalProvider(LocalUpsellColors provides extendedColors) {
        MaterialTheme(
            colorScheme = LuxuryDarkColorScheme,
            typography = AppTypography,
            shapes = LuxuryShapes,
            content = content,
        )
    }
}
