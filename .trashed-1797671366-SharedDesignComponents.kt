package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.CountdownClock
import com.example.ui.theme.LocalUpsellColors
import com.example.ui.theme.MonoFontFamily

@Composable
fun ResponsiveSectionWrapper(
    modifier: Modifier = Modifier,
    horizontalPadding: androidx.compose.ui.unit.Dp = 20.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 700.dp)
                .padding(horizontal = horizontalPadding),
            content = content,
        )
    }
}

@Composable
fun GoldSectionBadge(
    text: String,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Surface(
        modifier = modifier,
        color = colors.goldSoftTint,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, colors.goldPrimary.copy(alpha = 0.55f)),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = colors.goldHighlight,
                    modifier = Modifier.size(15.dp),
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = colors.goldHighlight,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun GoldAccentDivider(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        HorizontalDivider(
            modifier = Modifier.widthIn(max = 700.dp),
            thickness = 1.dp,
            color = colors.goldBorder.copy(alpha = 0.7f),
        )
    }
}

@Composable
fun PrimaryGoldCtaButton(
    text: String,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showArrow: Boolean = true,
) {
    val colors = LocalUpsellColors.current
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            colors.goldPrimary,
            colors.goldHighlight,
            colors.goldPrimary,
        ),
    )
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .testTag(testTag),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = colors.obsidianBackground,
        ),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradientBrush, RoundedCornerShape(14.dp))
                .border(
                    width = 1.dp,
                    color = colors.goldHighlight.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(14.dp),
                )
                .padding(horizontal = 18.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.obsidianBackground,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.weight(1f, fill = false),
                )
                if (showArrow) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = colors.obsidianBackground,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun Red24HourCountdownTimer(
    countdown: CountdownClock,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    val redGradient = Brush.verticalGradient(
        colors = listOf(
            colors.urgencyRedBright,
            colors.urgencyRed,
            colors.urgencyRedDark,
        ),
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(redGradient)
            .border(
                width = 1.5.dp,
                color = colors.urgencyRedBright,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(horizontal = 16.dp, vertical = 18.dp)
            .testTag("countdown_24h_red_box"),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Timer,
                contentDescription = null,
                tint = colors.textPrimary,
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = "CONDIÇÃO ESPECIAL VÁLIDA POR 24 HORAS",
                style = MaterialTheme.typography.labelMedium,
                color = colors.textPrimary,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            CountdownDigitTile(value = countdown.formattedHours, unit = "HORAS")
            Text(
                text = ":",
                fontFamily = MonoFontFamily,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textPrimary,
            )
            CountdownDigitTile(value = countdown.formattedMinutes, unit = "MINUTOS")
            Text(
                text = ":",
                fontFamily = MonoFontFamily,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textPrimary,
            )
            CountdownDigitTile(value = countdown.formattedSeconds, unit = "SEGUNDOS")
        }
    }
}

@Composable
private fun CountdownDigitTile(
    value: String,
    unit: String,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = Modifier
            .widthIn(min = 76.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colors.obsidianBackground.copy(alpha = 0.45f))
            .border(
                width = 1.dp,
                color = colors.textPrimary.copy(alpha = 0.28f),
                shape = RoundedCornerShape(10.dp),
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            fontFamily = MonoFontFamily,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colors.textPrimary,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = unit,
            style = MaterialTheme.typography.labelSmall,
            color = colors.textPrimary.copy(alpha = 0.85f),
            fontSize = 10.sp,
        )
    }
}
