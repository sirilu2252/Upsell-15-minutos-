package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.FaqItem
import com.example.data.TestimonialItem
import com.example.data.UpsellConfig
import com.example.ui.theme.LocalUpsellColors

/**
 * SECÇÃO 11 — PROVA SOCIAL (SEM TESTEMUNHOS FICTÍCIOS)
 */
@Composable
fun TestimonialsSection(
    testimonials: List<TestimonialItem>,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(text = "PROVA SOCIAL AUTÊNTICA", icon = Icons.Filled.FormatQuote)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.TESTIMONIALS_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (testimonials.isEmpty()) {
                // Espaço preparado para inserção posterior de depoimentos reais e autorizados
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = colors.cardSurface,
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, colors.goldBorder),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(colors.goldSoftTint)
                                .border(1.dp, colors.goldPrimary.copy(alpha = 0.5f), CircleShape),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.FormatQuote,
                                contentDescription = null,
                                tint = colors.goldHighlight,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Espaço reservado para depoimentos reais e autorizados",
                            style = MaterialTheme.typography.titleMedium,
                            color = colors.goldHighlight,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Esta secção está estruturada para exibir exclusivamente experiências autênticas (fotografia opcional, nome ou identificação autorizada, testemunho real e data/contexto) assim que forem adicionadas em UpsellConfig.testimonials. Nenhum relato fictício é exibido.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.textSecondary,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    testimonials.forEach { item ->
                        TestimonialCard(item = item)
                    }
                }
            }
        }
    }
}

@Composable
private fun TestimonialCard(
    item: TestimonialItem,
) {
    val colors = LocalUpsellColors.current
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = colors.cardSurface,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, colors.slateBorder),
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(colors.goldSoftTint)
                        .border(1.dp, colors.goldBorder, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    if (item.photoDrawableRes != null) {
                        Image(
                            painter = painterResource(id = item.photoDrawableRes),
                            contentDescription = "Foto de ${item.authorizedName}",
                            modifier = Modifier.size(44.dp),
                            contentScale = ContentScale.Crop,
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = colors.goldHighlight,
                        )
                    }
                }
                Column {
                    Text(
                        text = item.authorizedName,
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.textPrimary,
                        fontWeight = FontWeight.Bold,
                    )
                    if (!item.dateOrContext.isNullOrBlank()) {
                        Text(
                            text = item.dateOrContext,
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.textSecondary,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "\"${item.testimony}\"",
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textPrimary,
            )
        }
    }
}

/**
 * SECÇÃO 12 — INVERSÃO DE RISCO (GARANTIA DE 15 DIAS)
 */
@Composable
fun GuaranteeSection(
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.5.dp, colors.goldPrimary.copy(alpha = 0.7f)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_guarantee_seal),
                        contentDescription = stringResource(id = R.string.content_desc_guarantee_seal),
                        modifier = Modifier
                            .size(112.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, colors.goldPrimary, CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    GoldSectionBadge(
                        text = "15 DIAS DE GARANTIA INCONDICIONAL",
                        icon = Icons.Filled.VerifiedUser,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = UpsellConfig.GUARANTEE_TITLE,
                        style = MaterialTheme.typography.headlineMedium,
                        color = colors.goldHighlight,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = UpsellConfig.GUARANTEE_COPY,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

/**
 * SECÇÃO 13 — OBJEÇÕES E RESPOSTAS (PERGUNTAS FREQUENTES)
 */
@Composable
fun FaqSection(
    expandedFaqId: String?,
    onToggleFaq: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
    ) {
        ResponsiveSectionWrapper {
            GoldSectionBadge(
                text = "DÚVIDAS COMUNS",
                icon = Icons.AutoMirrored.Filled.HelpOutline,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = UpsellConfig.FAQ_TITLE,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                UpsellConfig.faqItems.forEach { faq ->
                    FaqAccordionItem(
                        item = faq,
                        isExpanded = expandedFaqId == faq.id,
                        onClick = { onToggleFaq(faq.id) },
                    )
                }
            }
        }
    }
}

@Composable
private fun FaqAccordionItem(
    item: FaqItem,
    isExpanded: Boolean,
    onClick: () -> Unit,
) {
    val colors = LocalUpsellColors.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .minimumInteractiveComponentSize()
            .testTag("faq_item_${item.id}"),
        color = if (isExpanded) colors.elevatedCardSurface else colors.cardSurface,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (isExpanded) colors.goldPrimary.copy(alpha = 0.7f) else colors.slateBorder,
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.question,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isExpanded) colors.goldHighlight else colors.textPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) "Fechar resposta" else "Abrir resposta",
                    tint = colors.goldHighlight,
                    modifier = Modifier.size(22.dp),
                )
            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = spring()) + fadeIn(),
                exit = shrinkVertically(animationSpec = spring()) + fadeOut(),
            ) {
                Text(
                    text = item.answer,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.textSecondary,
                    modifier = Modifier.padding(top = 12.dp),
                )
            }
        }
    }
}
