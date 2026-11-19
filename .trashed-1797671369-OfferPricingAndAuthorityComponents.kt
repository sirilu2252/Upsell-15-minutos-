package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UpsellConfig
import com.example.ui.CountdownClock
import com.example.ui.theme.DisplayFontFamily
import com.example.ui.theme.LocalUpsellColors

/**
 * SECÇÕES 9 & 10 — EMPILHAMENTO DE VALOR, ANCORAGEM DE PREÇO, ESCASSEZ,
 * CONTADOR VERMELHO DE 24 HORAS E EXCLUSIVIDADE
 */
@Composable
fun OfferAndPricingSection(
    countdown: CountdownClock,
    onAcceptOffer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(text = "OFERTA OFICIAL DO UPSELL", icon = Icons.Filled.LocalOffer)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.VALUE_STACK_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Secção 9 — Empilhamento de Valor (sem bônus inventados)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.5.dp, colors.goldPrimary),
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "OFERTA PRINCIPAL DO UPSELL:",
                        style = MaterialTheme.typography.labelMedium,
                        color = colors.goldHighlight,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = UpsellConfig.UPSELL_TITLE,
                        style = MaterialTheme.typography.titleLarge,
                        color = colors.textPrimary,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = colors.goldBorder)
                    Spacer(modifier = Modifier.height(16.dp))

                    ValueStackRow(label = "Formato", value = "Videoaula digital")
                    Spacer(modifier = Modifier.height(10.dp))
                    ValueStackRow(
                        label = "Conteúdo",
                        value = "Demonstração e orientação dos 17 exercícios",
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ValueStackRow(
                        label = "Aplicação",
                        value = "Rotina aproximada de 15 minutos por dia",
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ValueStackRow(
                        label = "Preço",
                        value = UpsellConfig.PROMO_PRICE,
                        highlight = true,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Secção 10 — Ancoragem de Valor
            Text(
                text = UpsellConfig.ANCHOR_TITLE,
                style = MaterialTheme.typography.headlineMedium,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = UpsellConfig.ANCHOR_COPY,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Bloco de Preço em Destaque + Escassez + Contador Vermelho 24h
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.elevatedCardSurface,
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.dp, colors.goldPrimary.copy(alpha = 0.7f)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "VALOR ORIGINAL: ${UpsellConfig.ORIGINAL_PRICE}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            textDecoration = TextDecoration.LineThrough,
                        ),
                        color = colors.textMuted,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = UpsellConfig.PROMO_PRICE,
                        fontFamily = DisplayFontFamily,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.goldHighlight,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Pagamento único • Acesso após confirmação",
                        style = MaterialTheme.typography.labelMedium,
                        color = colors.textSecondary,
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Texto obrigatório de Escassez
                    Text(
                        text = UpsellConfig.SCARCITY_COPY,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Contador de contagem regressivo de 24h de cor vermelha carregado
                    Red24HourCountdownTimer(countdown = countdown)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Texto obrigatório de Exclusividade
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = colors.obsidianBackground.copy(alpha = 0.75f),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, colors.goldBorder),
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = null,
                                tint = colors.goldPrimary,
                                modifier = Modifier.size(18.dp),
                            )
                            Text(
                                text = UpsellConfig.EXCLUSIVITY_COPY,
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.textSecondary,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    PrimaryGoldCtaButton(
                        text = UpsellConfig.PRIMARY_CTA_LABEL,
                        testTag = "pricing_accept_button",
                        onClick = onAcceptOffer,
                    )
                }
            }
        }
    }
}

@Composable
private fun ValueStackRow(
    label: String,
    value: String,
    highlight: Boolean = false,
) {
    val colors = LocalUpsellColors.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.titleMedium,
            color = colors.textSecondary,
            modifier = Modifier.padding(end = 12.dp),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = if (highlight) colors.goldHighlight else colors.textPrimary,
            fontWeight = if (highlight) FontWeight.ExtraBold else FontWeight.SemiBold,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f),
        )
    }
}

/**
 * COMPONENTE 10 — AUTORIDADE (CREDIBILIDADE VERIFICÁVEL E DEMONSTRAÇÃO ORGANIZADA)
 */
@Composable
fun AuthoritySection(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    val profile = UpsellConfig.authorityProfile
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(text = "AUTORIDADE & CREDIBILIDADE", icon = Icons.Filled.WorkspacePremium)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = profile.heading,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, colors.goldBorder),
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Shield,
                            contentDescription = null,
                            tint = colors.goldHighlight,
                            modifier = Modifier.size(22.dp),
                        )
                        Text(
                            text = profile.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = colors.goldHighlight,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = profile.verifiableSummary,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.textPrimary,
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        profile.methodologyPillars.forEach { pillar ->
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    tint = colors.goldPrimary,
                                    modifier = Modifier.size(18.dp),
                                )
                                Text(
                                    text = pillar,
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
