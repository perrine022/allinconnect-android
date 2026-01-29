package com.allinconnect.app.presentation.card

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.allinconnect.app.core.stripe.StripePaymentSheetHelper
import com.allinconnect.app.presentation.theme.AppGradient
import com.stripe.android.paymentsheet.PaymentSheetResult

@Composable
fun StripePaymentSheetScreen(
    clientSecret: String? = null,
    onNavigateBack: () -> Unit,
    onPaymentResult: (Boolean, String?) -> Unit,
    viewModel: StripePaymentSheetViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    
    if (activity == null) {
        // Show error if not ComponentActivity
        LaunchedEffect(Unit) {
            onPaymentResult(false, "Activity non supportée")
        }
        return
    }
    val helper = remember { StripePaymentSheetHelper() }
    
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val paymentSheetData by viewModel.paymentSheetData.collectAsStateWithLifecycle()
    
    LaunchedEffect(clientSecret, activity) {
        if (clientSecret != null && activity != null) {
            viewModel.loadPaymentSheetData(clientSecret)
        } else {
            viewModel.setError("Client secret manquant")
            onPaymentResult(false, "Client secret manquant")
        }
    }
    
    LaunchedEffect(paymentSheetData, activity) {
        if (paymentSheetData != null && activity != null) {
            try {
                helper.initialize(paymentSheetData!!.publishableKey, activity)
                val result = helper.presentPaymentSheet(
                    activity = activity,
                    clientSecret = paymentSheetData!!.paymentIntent,
                    customerId = paymentSheetData!!.customer,
                    ephemeralKeySecret = paymentSheetData!!.ephemeralKey
                )
                
                when (result) {
                    is PaymentSheetResult.Completed -> {
                        onPaymentResult(true, "Paiement réussi")
                    }
                    is PaymentSheetResult.Canceled -> {
                        onPaymentResult(false, "Paiement annulé")
                    }
                    is PaymentSheetResult.Failed -> {
                        onPaymentResult(false, result.error.message)
                    }
                }
            } catch (e: Exception) {
                viewModel.setError(e.message ?: "Erreur inconnue")
                onPaymentResult(false, e.message)
            }
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = androidx.compose.ui.graphics.Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Chargement du paiement...",
                    color = androidx.compose.ui.graphics.Color.White
                )
            } else if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onNavigateBack) {
                    Text("Retour")
                }
            }
        }
    }
}
