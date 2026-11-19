package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.ConversionEventType
import com.example.data.ConversionTracker
import com.example.data.UpsellConfig
import com.example.data.UtmParameters
import com.example.ui.FunnelDestination
import com.example.ui.UpsellViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `app name matches Método 15 Min`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Método 15 Min", appName)
    }

    @Test
    fun `config contains all 17 exercises and 6 faqs and no fake testimonials`() {
        assertEquals(17, UpsellConfig.exercises.size)
        assertEquals(6, UpsellConfig.faqItems.size)
        assertTrue(UpsellConfig.testimonials.isEmpty())
        assertEquals("https://pay.kursinha.com/c/6af2e8ff64e1a9b63f8859fb", UpsellConfig.UPSELL_CHECKOUT_URL)
    }

    @Test
    fun `checkout url preserves utm parameters`() {
        val utms = UtmParameters(
            utmSource = "facebook",
            utmMedium = "cpc",
            utmCampaign = "protocolo_upsell",
        )
        val builtUrl = UpsellConfig.buildCheckoutUrlWithUtms(utms)
        assertTrue(builtUrl.startsWith("https://pay.kursinha.com/c/6af2e8ff64e1a9b63f8859fb"))
        assertTrue(builtUrl.contains("utm_source=facebook"))
        assertTrue(builtUrl.contains("utm_medium=cpc"))
        assertTrue(builtUrl.contains("utm_campaign=protocolo_upsell"))
    }

    @Test
    fun `conversion tracker prevents duplicate view content and never fires purchase on checkout click`() {
        val tracker = ConversionTracker()
        val utms = UtmParameters(utmSource = "instagram")

        val firstView = tracker.trackViewContentOnce(utms)
        val duplicateView = tracker.trackViewContentOnce(utms)
        assertNotNull(firstView)
        assertNull(duplicateView)

        val initiate = tracker.trackInitiateCheckout(1, utms)
        assertNotNull(initiate)
        assertFalse(tracker.events.any { it.eventType == ConversionEventType.PURCHASE })

        val purchase = tracker.trackPurchaseAfterRealConfirmation("TXN-9981", utms)
        assertNotNull(purchase)
        assertTrue(tracker.events.any { it.eventType == ConversionEventType.PURCHASE })
    }

    @Test
    fun `view model handles decline and return to offer cleanly`() {
        val vm = UpsellViewModel()
        assertEquals(FunnelDestination.OfferPresentation, vm.uiState.value.destination)

        vm.declineUpsellOffer()
        assertEquals(FunnelDestination.DeclinedMainProductOnly, vm.uiState.value.destination)

        vm.returnToOffer()
        assertEquals(FunnelDestination.OfferPresentation, vm.uiState.value.destination)
    }
}
