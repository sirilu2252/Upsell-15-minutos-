package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.UpsellConfig
import com.example.ui.theme.LocalUpsellColors
import com.example.ui.theme.MonoFontFamily

/**
 * SECÇÃO 3 — IDENTIFICAÇÃO COM O DESEJO + MECANISMO ÚNICO (APRENDER / EXECUTAR / PRATICAR)
 */
@Composable
fun DesireIdentificationSection(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    val blockIcons = listOf(
        Icons.Filled.Bolt,
        Icons.Filled.OndemandVideo,
        Icons.Filled.Repeat,
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(text = "COMPLEMENTO ESTRATÉGICO", icon = Icons.Filled.FitnessCenter)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.DESIRE_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = UpsellConfig.DESIRE_BODY,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
            )

            Spacer(modifier = Modifier.height(22.dp))

            // 3 Blocos Visuais
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isWide = maxWidth >= 560.dp
                if (isWide) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        UpsellConfig.desireBlocks.forEachIndexed { index, (title, desc) ->
                            DesirePillarCard(
                                title = title,
                                description = desc,
                                icon = blockIcons[index % blockIcons.size],
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        UpsellConfig.desireBlocks.forEachIndexed { index, (title, desc) ->
                            DesirePillarCard(
                                title = title,
                                description = desc,
                                icon = blockIcons[index % blockIcons.size],
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Mecanismo Único em 3 Passos
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, colors.goldBorder),
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "COMO FUNCIONA O MECANISMO DO MÉTODO",
                        style = MaterialTheme.typography.labelMedium,
                        color = colors.goldHighlight,
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        UpsellConfig.mechanismSteps.forEach { (stepNum, stepTitle, stepDesc) ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(34.dp)
                                        .clip(CircleShape)
                                        .background(colors.goldSoftTint)
                                        .border(1.dp, colors.goldPrimary, CircleShape),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = stepNum,
                                        fontFamily = MonoFontFamily,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = colors.goldHighlight,
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = stepTitle,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = colors.textPrimary,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = stepDesc,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = colors.textSecondary,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DesirePillarCard(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Surface(
        modifier = modifier,
        color = colors.cardSurface,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, colors.slateBorder),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.goldSoftTint)
                    .border(1.dp, colors.goldBorder, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = colors.goldHighlight,
                    modifier = Modifier.size(20.dp),
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = colors.textPrimary,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.textSecondary,
            )
        }
    }
}

/**
 * SECÇÃO 4 — APRESENTAÇÃO DA SOLUÇÃO (VIDEOAULA + MOCKUP VISUAL)
 */
@Composable
fun VideoPresentationSection(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        colors.obsidianBackground,
                        colors.cardSurface.copy(alpha = 0.7f),
                        colors.obsidianBackground,
                    ),
                ),
            )
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(text = "EXPERIÊNCIA VISUAL", icon = Icons.Filled.OndemandVideo)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.SOLUTION_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.goldHighlight,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = UpsellConfig.SOLUTION_SUBTITLE,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textPrimary,
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Dispositivo com apresentação da videoaula + demonstração real em vídeo
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.dp, colors.goldPrimary.copy(alpha = 0.55f)),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "DEMONSTRAÇÃO DOS EXERCÍCIOS EM VÍDEO",
                        style = MaterialTheme.typography.labelMedium,
                        color = colors.goldHighlight,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Toque no leitor abaixo para assistir à prévia da demonstração prática dos exercícios diretamente nesta página.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.textSecondary,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ExerciseDemoVideoPlayer(
                        titleBadge = "VIDEOAULA • PRÉVIA",
                        testTagPrefix = "solution_demo_video",
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        UpsellConfig.solutionFeatures.forEach { feature ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    tint = colors.goldPrimary,
                                    modifier = Modifier.size(18.dp),
                                )
                                Text(
                                    text = feature,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = colors.textPrimary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
