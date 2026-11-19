package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.TrackedEventRecord
import com.example.data.UpsellConfig
import com.example.data.UtmParameters
import com.example.ui.theme.LocalUpsellColors
import com.example.ui.theme.MonoFontFamily

/**
 * SECÇÃO 14 — CTA FINAL (ACEITAR UPSELL OU RECUSAR DE FORMA OPCIONAL)
 */
@Composable
fun FinalCtaSection(
    onAcceptOffer: () -> Unit,
    onDeclineOffer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
    ) {
        ResponsiveSectionWrapper {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.cardSurface,
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.5.dp, colors.goldPrimary),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = UpsellConfig.FINAL_CTA_TITLE,
                        style = MaterialTheme.typography.headlineLarge,
                        color = colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = UpsellConfig.FINAL_CTA_COPY,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.textSecondary,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Resumo claro da oferta
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = colors.obsidianBackground.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, colors.goldBorder),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = UpsellConfig.UPSELL_SHORT_TITLE,
                                style = MaterialTheme.typography.titleLarge,
                                color = colors.goldHighlight,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = "17 exercícios de Kegel.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colors.textPrimary,
                            )
                            Text(
                                text = "Videoaula digital.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colors.textPrimary,
                            )
                            Text(
                                text = UpsellConfig.PROMO_PRICE,
                                style = MaterialTheme.typography.headlineMedium,
                                color = colors.goldHighlight,
                                fontWeight = FontWeight.ExtraBold,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    PrimaryGoldCtaButton(
                        text = UpsellConfig.PRIMARY_CTA_LABEL,
                        testTag = "upsell_accept_button",
                        onClick = onAcceptOffer,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = null,
                            tint = colors.textSecondary,
                            modifier = Modifier.size(14.dp),
                        )
                        Text(
                            text = UpsellConfig.CTA_MICROCOPY,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.textSecondary,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))
                    HorizontalDivider(color = colors.slateBorder)
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = onDeclineOffer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 52.dp)
                            .testTag("upsell_decline_button"),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, colors.slateBorder),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = colors.textSecondary,
                        ),
                    ) {
                        Text(
                            text = UpsellConfig.SECONDARY_CTA_LABEL,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.textSecondary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 6.dp),
                        )
                    }
                }
            }
        }
    }
}

/**
 * SECÇÃO 15 — RODAPÉ DISCRETO E PROFISSIONAL
 */
@Composable
fun UpsellFooter(
    onOpenTrackingDiagnostics: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalUpsellColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardSurface.copy(alpha = 0.55f))
            .navigationBarsPadding()
            .padding(top = 24.dp, bottom = 28.dp),
    ) {
        ResponsiveSectionWrapper {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = UpsellConfig.UPSELL_SHORT_TITLE,
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.goldPrimary,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Conteúdo digital complementar a ${UpsellConfig.MAIN_PRODUCT_FULL}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textSecondary,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Acesso disponibilizado por link após confirmação do pagamento. Garantia incondicional de 15 dias.",
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.textMuted,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextButton(
                    onClick = onOpenTrackingDiagnostics,
                    modifier = Modifier.testTag("open_tracking_diagnostics_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Analytics,
                        contentDescription = null,
                        tint = colors.textMuted,
                        modifier = Modifier.size(16.dp),
                    )
                    Text(
                        text = "  Configuração de Checkout, UTM & Rastreio",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.textMuted,
                    )
                }
            }
        }
    }
}

/**
 * Tela de saída do funil caso o comprador opte por não adicionar o upsell.
 */
@Composable
fun DeclinedFunnelScreen(
    onBackToOffer: () -> Unit,
) {
    BackHandler(onBack = onBackToOffer)
    val colors = LocalUpsellColors.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.obsidianBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = colors.cardSurface,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, colors.goldBorder),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = colors.goldHighlight,
                    modifier = Modifier.size(48.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "COMPRA PRINCIPAL CONFIRMADA",
                    style = MaterialTheme.typography.headlineMedium,
                    color = colors.goldHighlight,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "A videoaula extra não foi adicionada ao seu pedido. O seu acesso a \"${UpsellConfig.MAIN_PRODUCT_FULL}\" continua ativo e inalterado.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.textPrimary,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    color = colors.obsidianBackground,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, colors.slateBorder),
                ) {
                    Text(
                        text = "Destino de saída configurado: ${UpsellConfig.DECLINE_URL}",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.textSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(12.dp),
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedButton(
                    onClick = onBackToOffer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .testTag("return_to_upsell_button"),
                    border = BorderStroke(1.dp, colors.goldPrimary),
                ) {
                    Text(
                        text = "Voltar à apresentação",
                        style = MaterialTheme.typography.labelLarge,
                        color = colors.goldHighlight,
                    )
                }
            }
        }
    }
}

/**
 * Tela exibida exclusivamente após confirmação real de pagamento.
 */
@Composable
fun PaymentConfirmedScreen(
    transactionId: String,
    deliveryUrl: String,
    onOpenDeliveryUrl: (String) -> Unit,
    onBackToOffer: () -> Unit,
) {
    BackHandler(onBack = onBackToOffer)
    val colors = LocalUpsellColors.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.obsidianBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = colors.cardSurface,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.5.dp, colors.goldPrimary),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Filled.OndemandVideo,
                    contentDescription = null,
                    tint = colors.goldHighlight,
                    modifier = Modifier.size(52.dp),
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "PAGAMENTO CONFIRMADO!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = colors.goldHighlight,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "A sua inscrição na videoaula ${UpsellConfig.UPSELL_SHORT_TITLE} foi confirmada (ID: $transactionId). Evento Purchase registado com segurança.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.textPrimary,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(18.dp))
                if (deliveryUrl.isNotBlank()) {
                    PrimaryGoldCtaButton(
                        text = "ACEDER À VIDEOAULA AGORA",
                        testTag = "open_videoaula_delivery_button",
                        onClick = { onOpenDeliveryUrl(deliveryUrl) },
                    )
                } else {
                    Surface(
                        color = colors.obsidianBackground,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, colors.goldBorder),
                    ) {
                        Text(
                            text = "Link de entrega automática disponível após configuração de UpsellConfig.DELIVERY_VIDEOAULA_URL.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.textSecondary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(14.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TrackingDiagnosticsDialog(
    utms: UtmParameters,
    events: List<TrackedEventRecord>,
    lastCheckoutUrl: String?,
    onDismiss: () -> Unit,
) {
    val colors = LocalUpsellColors.current
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colors.cardSurface,
        titleContentColor = colors.goldHighlight,
        textContentColor = colors.textPrimary,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Rastreio, Checkout & UTM",
                    style = MaterialTheme.typography.titleLarge,
                    color = colors.goldHighlight,
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Fechar diagnóstico",
                        tint = colors.textSecondary,
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = "UPSELL_CHECKOUT_URL:\n${UpsellConfig.UPSELL_CHECKOUT_URL}",
                    fontFamily = MonoFontFamily,
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.textPrimary,
                )
                Text(
                    text = "DECLINE_URL: ${UpsellConfig.DECLINE_URL}",
                    fontFamily = MonoFontFamily,
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.textSecondary,
                )
                Text(
                    text = "Meta Pixel ID: ${UpsellConfig.META_PIXEL_ID.ifBlank { "A configurar em UpsellConfig" }}\nGA4 ID: ${UpsellConfig.GA4_MEASUREMENT_ID.ifBlank { "A configurar em UpsellConfig" }}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textSecondary,
                )
                Text(
                    text = "UTMs Ativos: " + if (utms.hasAny()) {
                        "source=${utms.utmSource}, medium=${utms.utmMedium}, campaign=${utms.utmCampaign}"
                    } else {
                        "Nenhum parâmetro UTM no Intent atual"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textSecondary,
                )
                if (!lastCheckoutUrl.isNullOrBlank()) {
                    Text(
                        text = "Último URL de checkout gerado:\n$lastCheckoutUrl",
                        fontFamily = MonoFontFamily,
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.goldHighlight,
                    )
                }
                HorizontalDivider(color = colors.slateBorder)
                Text(
                    text = "Eventos Registados (${events.size}):",
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.textPrimary,
                )
                events.forEach { ev ->
                    Surface(
                        color = colors.obsidianBackground,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, colors.slateBorder),
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "${ev.eventType.eventName} • ${ev.valueKz} Kz",
                                fontFamily = MonoFontFamily,
                                style = MaterialTheme.typography.labelSmall,
                                color = colors.goldHighlight,
                            )
                            Text(
                                text = ev.note,
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.textSecondary,
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.goldPrimary,
                    contentColor = colors.obsidianBackground,
                ),
            ) {
                Text("Fechar", fontWeight = FontWeight.Bold)
            }
        },
    )
}
