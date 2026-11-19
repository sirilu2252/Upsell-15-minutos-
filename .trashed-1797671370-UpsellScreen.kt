package com.example.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.R
import com.example.data.UpsellConfig
import com.example.ui.components.AuthoritySection
import com.example.ui.components.BenefitsSection
import com.example.ui.components.DeclinedFunnelScreen
import com.example.ui.components.DesireIdentificationSection
import com.example.ui.components.DifferentiationSection
import com.example.ui.components.ExercisesGridSection
import com.example.ui.components.FaqSection
import com.example.ui.components.FinalCtaSection
import com.example.ui.components.GoldAccentDivider
import com.example.ui.components.GuaranteeSection
import com.example.ui.components.HeroSection
import com.example.ui.components.OfferAndPricingSection
import com.example.ui.components.PaymentConfirmedScreen
import com.example.ui.components.PurchaseCompletedHeader
import com.example.ui.components.RoutineSimplicitySection
import com.example.ui.components.TestimonialsSection
import com.example.ui.components.TrackingDiagnosticsDialog
import com.example.ui.components.UpsellFooter
import com.example.ui.components.VideoPresentationSection
import com.example.ui.theme.LocalUpsellColors
import kotlinx.coroutines.launch

@Composable
fun UpsellAppRoot(
    viewModel: UpsellViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val launchExternalUrl: (String) -> Unit = { url ->
        openExternalUrl(context = context, url = url)
    }

    when (val dest = uiState.destination) {
        is FunnelDestination.OfferPresentation -> {
            UpsellPresentationScreen(
                uiState = uiState,
                onToggleExercise = viewModel::toggleExercise,
                onToggleFaq = viewModel::toggleFaq,
                onAcceptOffer = {
                    val checkoutUrl = viewModel.prepareCheckoutLaunch()
                    launchExternalUrl(checkoutUrl)
                },
                onDeclineOffer = viewModel::declineUpsellOffer,
                onOpenTrackingDiagnostics = { viewModel.setTrackingDiagnosticsVisible(true) },
                onDismissTrackingDiagnostics = { viewModel.setTrackingDiagnosticsVisible(false) },
            )
        }

        is FunnelDestination.DeclinedMainProductOnly -> {
            DeclinedFunnelScreen(
                onBackToOffer = viewModel::returnToOffer,
            )
        }

        is FunnelDestination.PaymentConfirmed -> {
            PaymentConfirmedScreen(
                transactionId = dest.transactionId,
                deliveryUrl = dest.deliveryUrl,
                onOpenDeliveryUrl = launchExternalUrl,
                onBackToOffer = viewModel::returnToOffer,
            )
        }
    }
}

@Composable
private fun UpsellPresentationScreen(
    uiState: UpsellUiState,
    onToggleExercise: (Int) -> Unit,
    onToggleFaq: (String) -> Unit,
    onAcceptOffer: () -> Unit,
    onDeclineOffer: () -> Unit,
    onOpenTrackingDiagnostics: () -> Unit,
    onDismissTrackingDiagnostics: () -> Unit,
) {
    val colors = LocalUpsellColors.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val scrollToOfferSection: () -> Unit = {
        coroutineScope.launch {
            // Item index 15 corresponds to section_9_offer (OfferAndPricingSection)
            listState.animateScrollToItem(index = 15)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.obsidianBackground)
            .statusBarsPadding(),
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 0.dp),
        ) {
            // 1. Header de compra concluída
            item(key = "section_1_header") {
                PurchaseCompletedHeader()
            }

            // 2. Hero principal
            item(key = "section_2_hero") {
                HeroSection(onCtaClick = scrollToOfferSection)
            }
            item(key = "div_2") { GoldAccentDivider() }

            // 3. Identificação com o desejo
            item(key = "section_3_desire") {
                DesireIdentificationSection()
            }
            item(key = "div_3") { GoldAccentDivider() }

            // 4. Apresentação da videoaula
            item(key = "section_4_solution") {
                VideoPresentationSection()
            }
            item(key = "div_4") { GoldAccentDivider() }

            // 5. Os 17 exercícios
            item(key = "section_5_exercises") {
                ExercisesGridSection(
                    expandedExerciseNumber = uiState.expandedExerciseNumber,
                    onToggleExercise = onToggleExercise,
                )
            }
            item(key = "div_5") { GoldAccentDivider() }

            // 6. Rotina de 15 minutos
            item(key = "section_6_routine") {
                RoutineSimplicitySection()
            }
            item(key = "div_6") { GoldAccentDivider() }

            // 7. Benefícios
            item(key = "section_7_benefits") {
                BenefitsSection()
            }
            item(key = "div_7") { GoldAccentDivider() }

            // 8. Diferenciação
            item(key = "section_8_differentiation") {
                DifferentiationSection()
            }
            item(key = "div_8") { GoldAccentDivider() }

            // 9 & 10. Oferta, empilhamento de valor, ancoragem, escassez, exclusividade e contador 24h
            item(key = "section_9_offer") {
                OfferAndPricingSection(
                    countdown = uiState.countdown,
                    onAcceptOffer = onAcceptOffer,
                )
            }
            item(key = "div_9") { GoldAccentDivider() }

            // 10b. Autoridade
            item(key = "section_10_authority") {
                AuthoritySection()
            }
            item(key = "div_10") { GoldAccentDivider() }

            // 11. Prova Social (Depoimentos)
            item(key = "section_11_testimonials") {
                TestimonialsSection(testimonials = UpsellConfig.testimonials)
            }
            item(key = "div_11") { GoldAccentDivider() }

            // 12. Garantia
            item(key = "section_12_guarantee") {
                GuaranteeSection()
            }
            item(key = "div_12") { GoldAccentDivider() }

            // 13. Perguntas frequentes
            item(key = "section_13_faq") {
                FaqSection(
                    expandedFaqId = uiState.expandedFaqId,
                    onToggleFaq = onToggleFaq,
                )
            }
            item(key = "div_13") { GoldAccentDivider() }

            // 14. CTA final
            item(key = "section_14_final_cta") {
                FinalCtaSection(
                    onAcceptOffer = onAcceptOffer,
                    onDeclineOffer = onDeclineOffer,
                )
            }

            // 15. Rodapé
            item(key = "section_15_footer") {
                UpsellFooter(onOpenTrackingDiagnostics = onOpenTrackingDiagnostics)
            }
        }

        if (uiState.showTrackingDiagnostics) {
            TrackingDiagnosticsDialog(
                utms = uiState.utmParameters,
                events = uiState.trackedEvents,
                lastCheckoutUrl = uiState.lastCheckoutUrlLaunched,
                onDismiss = onDismissTrackingDiagnostics,
            )
        }
    }
}

private fun openExternalUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(
            context,
            context.getString(R.string.checkout_open_error),
            Toast.LENGTH_LONG,
        ).show()
    }
}
