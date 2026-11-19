package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.UpsellConfig
import com.example.ui.theme.LocalUpsellColors

/**
 * SECÇÃO 7 — TRANSFORMAÇÃO E BENEFÍCIOS
 */
@Composable
fun BenefitsSection(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(text = "TRANSFORMAÇÃO PRÁTICA", icon = Icons.Filled.Star)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.BENEFITS_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                UpsellConfig.benefitsList.forEach { benefit ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = colors.cardSurface,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, colors.slateBorder),
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = colors.goldHighlight,
                                modifier = Modifier.size(20.dp),
                            )
                            Text(
                                text = benefit,
                                style = MaterialTheme.typography.bodyLarge,
                                color = colors.textPrimary,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.goldSoftTint,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, colors.goldPrimary.copy(alpha = 0.65f)),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Verified,
                        contentDescription = null,
                        tint = colors.goldHighlight,
                        modifier = Modifier.size(22.dp),
                    )
                    Text(
                        text = UpsellConfig.BENEFITS_CLOSING,
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.goldHighlight,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

/**
 * SECÇÃO 8 — DIFERENCIAÇÃO (APENAS CONHECIMENTO VS VIDEOAULA)
 */
@Composable
fun DifferentiationSection(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(text = "DIFERENÇA NA PRÁTICA", icon = Icons.Filled.CompareArrows)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.DIFFERENTIATION_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.DIFFERENTIATION_COPY,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
            )
            Spacer(modifier = Modifier.height(18.dp))

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isWide = maxWidth >= 560.dp
                if (isWide) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        ComparisonColumnCard(
                            title = "APENAS CONHECIMENTO",
                            points = UpsellConfig.knowledgeOnlyPoints,
                            isRecommended = false,
                            modifier = Modifier.weight(1f),
                        )
                        ComparisonColumnCard(
                            title = "VIDEOAULA",
                            points = UpsellConfig.videoLessonPoints,
                            isRecommended = true,
                            modifier = Modifier.weight(1f),
                        )
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        ComparisonColumnCard(
                            title = "APENAS CONHECIMENTO",
                            points = UpsellConfig.knowledgeOnlyPoints,
                            isRecommended = false,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        ComparisonColumnCard(
                            title = "VIDEOAULA",
                            points = UpsellConfig.videoLessonPoints,
                            isRecommended = true,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ComparisonColumnCard(
    title: String,
    points: List<String>,
    isRecommended: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Surface(
        modifier = modifier,
        color = if (isRecommended) colors.elevatedCardSurface else colors.cardSurface,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if (isRecommended) 1.5.dp else 1.dp,
            color = if (isRecommended) colors.goldPrimary else colors.slateBorder,
        ),
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = if (isRecommended) colors.goldHighlight else colors.textSecondary,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.height(14.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                points.forEach { point ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Icon(
                            imageVector = if (isRecommended) Icons.Filled.CheckCircle else Icons.Filled.Close,
                            contentDescription = null,
                            tint = if (isRecommended) colors.goldHighlight else colors.textMuted,
                            modifier = Modifier.size(19.dp),
                        )
                        Text(
                            text = point,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (isRecommended) colors.textPrimary else colors.textSecondary,
                        )
                    }
                }
            }
        }
    }
}
