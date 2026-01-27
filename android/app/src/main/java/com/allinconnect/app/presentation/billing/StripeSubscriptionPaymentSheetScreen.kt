package com.allinconnect.app.presentation.billing

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.allinconnect.app.core.stripe.StripePaymentSheetHelper
import com.allinconnect.app.presentation.theme.AppGradient
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.launch

@Composable
fun StripeSubscriptionPaymentSheetScreen(
    clientSecret: String? = null,
    customerId: String? = null,
    ephemeralKeySecret: String? = null,
    publishableKey: String? = null,
    onNavigateBack: () -> Unit,
    onPaymentResult: (Boolean, String?) -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()
    val helper = remember { StripePaymentSheetHelper() }
    
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    LaunchedEffect(clientSecret, customerId, ephemeralKeySecret, publishableKey, activity) {
        if (clientSecret != null && publishableKey != null && activity != null) {
            isLoading = true
            try {
                helper.initialize(publishableKey, activity)
                val result = helper.presentPaymentSheet(
                    activity = activity,
                    clientSecret = clientSecret,
                    customerId = customerId,
                    ephemeralKeySecret = ephemeralKeySecret
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
                errorMessage = e.message
                onPaymentResult(false, e.message)
            } finally {
                isLoading = false
            }
        } else {
            errorMessage = "Données de paiement manquantes"
            onPaymentResult(false, "Données de paiement manquantes")
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
