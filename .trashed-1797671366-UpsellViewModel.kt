package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ConversionTracker
import com.example.data.TrackedEventRecord
import com.example.data.UpsellConfig
import com.example.data.UtmParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

sealed interface FunnelDestination {
    data object OfferPresentation : FunnelDestination
    data object DeclinedMainProductOnly : FunnelDestination
    data class PaymentConfirmed(val transactionId: String, val deliveryUrl: String) : FunnelDestination
}

data class CountdownClock(
    val hours: Int = 23,
    val minutes: Int = 59,
    val seconds: Int = 59,
) {
    val formattedHours: String get() = hours.toString().padStart(2, '0')
    val formattedMinutes: String get() = minutes.toString().padStart(2, '0')
    val formattedSeconds: String get() = seconds.toString().padStart(2, '0')
}

data class UpsellUiState(
    val destination: FunnelDestination = FunnelDestination.OfferPresentation,
    val countdown: CountdownClock = CountdownClock(),
    val expandedExerciseNumber: Int? = 1,
    val showAllExercises: Boolean = true,
    val expandedFaqId: String? = "faq_equipment",
    val utmParameters: UtmParameters = UtmParameters(),
    val trackedEvents: List<TrackedEventRecord> = emptyList(),
    val showTrackingDiagnostics: Boolean = false,
    val checkoutLaunchCount: Int = 0,
    val lastCheckoutUrlLaunched: String? = null,
)

class UpsellViewModel : ViewModel() {

    private val tracker = ConversionTracker()

    private val _uiState = MutableStateFlow(UpsellUiState())
    val uiState: StateFlow<UpsellUiState> = _uiState.asStateFlow()

    private var remainingTotalSeconds: Int = 24 * 60 * 60 - 1 // 23:59:59 loop

    init {
        startInfinite24HourCountdown()
        registerViewContent()
    }

    private fun startInfinite24HourCountdown() {
        viewModelScope.launch {
            while (isActive) {
                val hrs = remainingTotalSeconds / 3600
                val mins = (remainingTotalSeconds % 3600) / 60
                val secs = remainingTotalSeconds % 60
                _uiState.update { state ->
                    state.copy(countdown = CountdownClock(hours = hrs, minutes = mins, seconds = secs))
                }
                delay(1000L)
                remainingTotalSeconds = if (remainingTotalSeconds > 0) {
                    remainingTotalSeconds - 1
                } else {
                    24 * 60 * 60 - 1 // Reinicia automaticamente para ciclo infinito de 24h
                }
            }
        }
    }

    private fun registerViewContent() {
        val currentUtms = _uiState.value.utmParameters
        tracker.trackViewContentOnce(currentUtms)
        _uiState.update { it.copy(trackedEvents = tracker.events) }
    }

    fun applyUtmParameters(utms: UtmParameters) {
        _uiState.update { it.copy(utmParameters = utms) }
    }

    fun toggleExercise(number: Int) {
        _uiState.update { state ->
            val next = if (state.expandedExerciseNumber == number) null else number
            state.copy(expandedExerciseNumber = next)
        }
    }

    fun toggleShowAllExercises() {
        _uiState.update { state ->
            state.copy(showAllExercises = !state.showAllExercises)
        }
    }

    fun toggleFaq(faqId: String) {
        _uiState.update { state ->
            val next = if (state.expandedFaqId == faqId) null else faqId
            state.copy(expandedFaqId = next)
        }
    }

    fun prepareCheckoutLaunch(): String {
        val nextSequence = _uiState.value.checkoutLaunchCount + 1
        val utms = _uiState.value.utmParameters
        val checkoutUrl = UpsellConfig.buildCheckoutUrlWithUtms(utms)
        tracker.trackInitiateCheckout(checkoutClickSequence = nextSequence, utms = utms)
        _uiState.update {
            it.copy(
                checkoutLaunchCount = nextSequence,
                lastCheckoutUrlLaunched = checkoutUrl,
                trackedEvents = tracker.events,
            )
        }
        return checkoutUrl
    }

    /**
     * Chamado apenas quando um callback real de pagamento confirmado retorna ao app.
     */
    fun onRealPaymentConfirmed(transactionId: String, deliveryUrl: String) {
        val utms = _uiState.value.utmParameters
        tracker.trackPurchaseAfterRealConfirmation(transactionId = transactionId, utms = utms)
        _uiState.update {
            it.copy(
                destination = FunnelDestination.PaymentConfirmed(
                    transactionId = transactionId,
                    deliveryUrl = deliveryUrl,
                ),
                trackedEvents = tracker.events,
            )
        }
    }

    fun declineUpsellOffer() {
        _uiState.update { it.copy(destination = FunnelDestination.DeclinedMainProductOnly) }
    }

    fun returnToOffer() {
        _uiState.update { it.copy(destination = FunnelDestination.OfferPresentation) }
    }

    fun setTrackingDiagnosticsVisible(visible: Boolean) {
        _uiState.update { it.copy(showTrackingDiagnostics = visible) }
    }
}
