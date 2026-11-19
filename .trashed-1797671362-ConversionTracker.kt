package com.example.data

import android.net.Uri
import android.util.Log

data class UtmParameters(
    val utmSource: String = "",
    val utmMedium: String = "",
    val utmCampaign: String = "",
    val utmContent: String = "",
    val utmTerm: String = "",
) {
    fun hasAny(): Boolean =
        utmSource.isNotBlank() ||
            utmMedium.isNotBlank() ||
            utmCampaign.isNotBlank() ||
            utmContent.isNotBlank() ||
            utmTerm.isNotBlank()

    companion object {
        fun fromUri(uri: Uri?): UtmParameters {
            if (uri == null) return UtmParameters()
            return UtmParameters(
                utmSource = uri.getQueryParameter("utm_source").orEmpty(),
                utmMedium = uri.getQueryParameter("utm_medium").orEmpty(),
                utmContent = uri.getQueryParameter("utm_content").orEmpty(),
                utmCampaign = uri.getQueryParameter("utm_campaign").orEmpty(),
                utmTerm = uri.getQueryParameter("utm_term").orEmpty(),
            )
        }
    }
}

enum class TrackingPlatform {
    META_PIXEL,
    GA4,
}

enum class ConversionEventType(val eventName: String) {
    VIEW_CONTENT("ViewContent"),
    INITIATE_CHECKOUT("InitiateCheckout"),
    PURCHASE("Purchase"),
}

data class TrackedEventRecord(
    val eventType: ConversionEventType,
    val timestampMillis: Long,
    val platforms: List<TrackingPlatform>,
    val valueKz: Int,
    val utms: UtmParameters,
    val note: String,
)

/**
 * Gestor de eventos de rastreio e conversão (Meta Pixel + GA4) com proteção
 * contra duplicação e garantia de que Purchase só dispara após confirmação real.
 */
class ConversionTracker {

    private val dispatchedKeys = mutableSetOf<String>()
    private val _events = mutableListOf<TrackedEventRecord>()
    val events: List<TrackedEventRecord> get() = _events.toList()

    fun trackViewContentOnce(utms: UtmParameters): TrackedEventRecord? {
        val dedupKey = "view_content_session"
        if (!dispatchedKeys.add(dedupKey)) {
            return null
        }
        return recordEvent(
            eventType = ConversionEventType.VIEW_CONTENT,
            valueKz = 3000,
            utms = utms,
            note = "Visualização inicial da oferta de upsell registada sem duplicação.",
        )
    }

    fun trackInitiateCheckout(
        checkoutClickSequence: Int,
        utms: UtmParameters,
    ): TrackedEventRecord? {
        val dedupKey = "initiate_checkout_$checkoutClickSequence"
        if (!dispatchedKeys.add(dedupKey)) {
            return null
        }
        return recordEvent(
            eventType = ConversionEventType.INITIATE_CHECKOUT,
            valueKz = 3000,
            utms = utms,
            note = "Redirecionamento para checkout externo (Purchase NÃO disparado no clique).",
        )
    }

    /**
     * Deve ser invocado EXCLUSIVAMENTE após confirmação real de pagamento recebida
     * via webhook/callback/deep link de retorno da plataforma de pagamento.
     */
    fun trackPurchaseAfterRealConfirmation(
        transactionId: String,
        utms: UtmParameters,
    ): TrackedEventRecord? {
        if (transactionId.isBlank()) return null
        val dedupKey = "purchase_$transactionId"
        if (!dispatchedKeys.add(dedupKey)) {
            return null
        }
        return recordEvent(
            eventType = ConversionEventType.PURCHASE,
            valueKz = 3000,
            utms = utms,
            note = "Pagamento confirmado externamente (ID: $transactionId).",
        )
    }

    private fun recordEvent(
        eventType: ConversionEventType,
        valueKz: Int,
        utms: UtmParameters,
        note: String,
    ): TrackedEventRecord {
        val activePlatforms = buildList {
            add(TrackingPlatform.META_PIXEL)
            add(TrackingPlatform.GA4)
        }
        val record = TrackedEventRecord(
            eventType = eventType,
            timestampMillis = System.currentTimeMillis(),
            platforms = activePlatforms,
            valueKz = valueKz,
            utms = utms,
            note = note,
        )
        _events.add(record)
        Log.d(
            "ConversionTracker",
            "Dispatched ${eventType.eventName} | value=${valueKz}Kz | pixel=${UpsellConfig.META_PIXEL_ID.ifBlank { "configurar" }} | ga4=${UpsellConfig.GA4_MEASUREMENT_ID.ifBlank { "configurar" }} | utm_source=${utms.utmSource}",
        )
        return record
    }
}
