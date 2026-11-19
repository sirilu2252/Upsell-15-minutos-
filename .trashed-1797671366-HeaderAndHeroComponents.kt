package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayCircleFilled
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.UpsellConfig
import com.example.ui.theme.LocalUpsellColors

/**
 * SECÇÃO 1 — HEADER DE COMPRA CONCLUÍDA
 */
@Composable
fun PurchaseCompletedHeader(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        colors.cardSurface,
                        colors.obsidianBackground,
                    ),
                ),
            )
            .padding(top = 18.dp, bottom = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, colors.goldPrimary.copy(alpha = 0.65f)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    GoldSectionBadge(
                        text = UpsellConfig.HEADER_BADGE,
                        icon = Icons.Filled.AutoAwesome,
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = colors.goldHighlight,
                            modifier = Modifier.size(22.dp),
                        )
                        Text(
                            text = UpsellConfig.HEADER_CONGRATS,
                            style = MaterialTheme.typography.titleLarge,
                            color = colors.goldHighlight,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = UpsellConfig.HEADER_SUBTITLE,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        color = colors.obsidianBackground.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, colors.slateBorder),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = null,
                                tint = colors.goldPrimary,
                                modifier = Modifier.size(16.dp),
                            )
                            Text(
                                text = UpsellConfig.HEADER_DISCLAIMER,
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
 * SECÇÃO 2 — HERO PRINCIPAL + GATILHO DE URGÊNCIA CONTEXTUAL
 */
@Composable
fun HeroSection(
    onCtaClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        ResponsiveSectionWrapper {
            // Hero Discipline Atmospheric Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = colors.goldBorder,
                        shape = RoundedCornerShape(20.dp),
                    ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_discipline),
                    contentDescription = stringResource(id = R.string.content_desc_hero_image),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    colors.obsidianBackground.copy(alpha = 0.88f),
                                ),
                            ),
                        ),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                ) {
                    GoldSectionBadge(
                        text = UpsellConfig.UPSELL_SHORT_TITLE,
                        icon = Icons.Filled.PlayCircleFilled,
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = UpsellConfig.HERO_HEADLINE,
                style = MaterialTheme.typography.displaySmall,
                color = colors.textPrimary,
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = UpsellConfig.HERO_SUBHEADLINE,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Frase de destaque
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.goldSoftTint,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, colors.goldPrimary.copy(alpha = 0.7f)),
            ) {
                Text(
                    text = UpsellConfig.HERO_HIGHLIGHT,
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.goldHighlight,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Urgência contextual da jornada de compra
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, colors.slateBorder),
            ) {
                Text(
                    text = UpsellConfig.URGENCY_CONTEXT_COPY,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textPrimary.copy(alpha = 0.92f),
                    modifier = Modifier.padding(16.dp),
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            PrimaryGoldCtaButton(
                text = "VER OFERTA EXCLUSIVA POR ${UpsellConfig.PROMO_PRICE}",
                testTag = "hero_scroll_to_offer_button",
                onClick = onCtaClick,
            )
        }
    }
}
