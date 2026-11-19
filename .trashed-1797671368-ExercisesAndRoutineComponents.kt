package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.KegelExerciseItem
import com.example.data.UpsellConfig
import com.example.ui.theme.LocalUpsellColors
import com.example.ui.theme.MonoFontFamily

/**
 * SECÇÃO 5 — OS 17 EXERCÍCIOS DE KEGEL
 */
@Composable
fun ExercisesGridSection(
    expandedExerciseNumber: Int?,
    onToggleExercise: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    var showVideoModal by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(
                text = "CONTEÚDO DA VIDEOAULA • 17 EXERCÍCIOS",
                icon = Icons.Filled.FitnessCenter,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.EXERCISES_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Cada exercício leva apenas 45 segundos para ser realizado. Toque em qualquer cartão para ver os espaços preparados para demonstração em vídeo e explicação prática da aula.",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.textSecondary,
            )

            Spacer(modifier = Modifier.height(18.dp))

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isTwoColumn = maxWidth >= 580.dp
                if (isTwoColumn) {
                    val rows = UpsellConfig.exercises.chunked(2)
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        rows.forEach { pair ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                pair.forEach { item ->
                                    ExerciseCard(
                                        item = item,
                                        isExpanded = expandedExerciseNumber == item.number,
                                        onClick = { onToggleExercise(item.number) },
                                        onWatchVideo = { showVideoModal = true },
                                        modifier = Modifier.weight(1f),
                                    )
                                }
                                if (pair.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        UpsellConfig.exercises.forEach { item ->
                            ExerciseCard(
                                item = item,
                                isExpanded = expandedExerciseNumber == item.number,
                                onClick = { onToggleExercise(item.number) },
                                onWatchVideo = { showVideoModal = true },
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        }
    }

    if (showVideoModal) {
        FullscreenVideoDialog(
            rawResId = R.raw.video_exercicios_demo,
            onDismiss = { showVideoModal = false },
        )
    }
}

@Composable
private fun ExerciseCard(
    item: KegelExerciseItem,
    isExpanded: Boolean,
    onClick: () -> Unit,
    onWatchVideo: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .minimumInteractiveComponentSize()
            .testTag("exercise_card_${item.code}"),
        color = if (isExpanded) colors.elevatedCardSurface else colors.cardSurface,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (isExpanded) colors.goldPrimary.copy(alpha = 0.7f) else colors.slateBorder,
        ),
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(colors.goldSoftTint)
                        .border(1.dp, colors.goldPrimary.copy(alpha = 0.6f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = item.code,
                        fontFamily = MonoFontFamily,
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.goldHighlight,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.textPrimary,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Schedule,
                            contentDescription = null,
                            tint = colors.goldPrimary,
                            modifier = Modifier.size(13.dp),
                        )
                        Text(
                            text = item.durationLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.goldPrimary,
                        )
                    }
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) "Recolher detalhes do exercício ${item.code}" else "Expandir detalhes do exercício ${item.code}",
                    tint = colors.goldHighlight,
                    modifier = Modifier.size(22.dp),
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = spring()) + fadeIn(),
                exit = shrinkVertically(animationSpec = spring()) + fadeOut(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = colors.obsidianBackground.copy(alpha = 0.75f),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, colors.goldBorder),
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PlayCircleOutline,
                                contentDescription = null,
                                tint = colors.goldHighlight,
                                modifier = Modifier.size(20.dp),
                            )
                            Column {
                                Text(
                                    text = "DEMONSTRAÇÃO NA VIDEOAULA",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = colors.goldHighlight,
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = item.demonstrationPlaceholder,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colors.textSecondary,
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                TextButton(
                                    onClick = onWatchVideo,
                                    modifier = Modifier
                                        .minimumInteractiveComponentSize()
                                        .testTag("watch_demo_exercise_${item.code}"),
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.PlayCircleOutline,
                                        contentDescription = null,
                                        tint = colors.goldHighlight,
                                        modifier = Modifier.size(18.dp),
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Assistir demonstração em vídeo",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = colors.goldHighlight,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                        }
                    }

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = colors.obsidianBackground.copy(alpha = 0.75f),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, colors.slateBorder),
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "EXPLICAÇÃO PRÁTICA DO EXERCÍCIO",
                                style = MaterialTheme.typography.labelSmall,
                                color = colors.textPrimary,
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = item.explanationPlaceholder,
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

/**
 * SECÇÃO 6 — A SIMPLICIDADE DA ROTINA
 */
@Composable
fun RoutineSimplicitySection(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(text = "ROTINA PRÁTICA", icon = Icons.Filled.AccessTime)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.ROUTINE_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.ROUTINE_COPY,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Fluxo visual: 17 exercícios -> aproximadamente 15 minutos por dia -> prática consistente
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, colors.goldPrimary.copy(alpha = 0.55f)),
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                ) {
                    val isWide = maxWidth >= 520.dp
                    if (isWide) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            RoutineFlowStep(
                                number = "17",
                                label = "exercícios\n(45 seg cada)",
                                modifier = Modifier.weight(1f),
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = colors.goldHighlight,
                                modifier = Modifier.padding(horizontal = 8.dp),
                            )
                            RoutineFlowStep(
                                number = "~15 min",
                                label = "por dia\nna sua rotina",
                                modifier = Modifier.weight(1f),
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = colors.goldHighlight,
                                modifier = Modifier.padding(horizontal = 8.dp),
                            )
                            RoutineFlowStep(
                                number = "100%",
                                label = "prática\nconsistente",
                                modifier = Modifier.weight(1f),
                            )
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            RoutineFlowStep(
                                number = "17 exercícios",
                                label = "Cada exercício leva apenas 45 segundos",
                                modifier = Modifier.fillMaxWidth(),
                            )
                            Text(
                                text = "↓",
                                style = MaterialTheme.typography.titleLarge,
                                color = colors.goldHighlight,
                            )
                            RoutineFlowStep(
                                number = "Aproximadamente 15 minutos por dia",
                                label = "Fácil de encaixar no seu dia a dia",
                                modifier = Modifier.fillMaxWidth(),
                            )
                            Text(
                                text = "↓",
                                style = MaterialTheme.typography.titleLarge,
                                color = colors.goldHighlight,
                            )
                            RoutineFlowStep(
                                number = "Prática consistente",
                                label = "Consciência e controlo da musculatura do assoalho pélvico",
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RoutineFlowStep(
    number: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Surface(
        modifier = modifier,
        color = colors.obsidianBackground.copy(alpha = 0.7f),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, colors.goldBorder),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = number,
                fontFamily = MonoFontFamily,
                style = MaterialTheme.typography.titleLarge,
                color = colors.goldHighlight,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.textSecondary,
                textAlign = TextAlign.Center,
            )
        }
    }
}
