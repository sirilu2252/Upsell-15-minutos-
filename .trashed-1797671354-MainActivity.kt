package com.example

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.data.UpsellConfig
import com.example.data.UtmParameters
import com.example.ui.UpsellAppRoot
import com.example.ui.UpsellViewModel
import com.example.ui.theme.Metodo15MinTheme

class MainActivity : ComponentActivity() {

    private val viewModel: UpsellViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        handleIncomingIntent(intent)
        setContent {
            Metodo15MinTheme {
                UpsellAppRoot(viewModel = viewModel)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIncomingIntent(intent)
    }

    private fun handleIncomingIntent(intent: Intent?) {
        val data = intent?.data ?: return
        val utms = UtmParameters.fromUri(data)
        if (utms.hasAny()) {
            viewModel.applyUtmParameters(utms)
        }
        // Apenas confirma Purchase caso o retorno venha com status pago real e ID de transação
        val paymentStatus = data.getQueryParameter("payment_status")
        val transactionId = data.getQueryParameter("transaction_id")
        if (paymentStatus == "approved" && !transactionId.isNullOrBlank()) {
            val deliveryUrl = data.getQueryParameter("delivery_url")
                ?.takeIf { it.isNotBlank() }
                ?: UpsellConfig.DELIVERY_VIDEOAULA_URL
            viewModel.onRealPaymentConfirmed(
                transactionId = transactionId,
                deliveryUrl = deliveryUrl,
            )
        }
    }
}
