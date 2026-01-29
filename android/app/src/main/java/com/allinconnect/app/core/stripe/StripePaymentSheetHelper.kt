package com.allinconnect.app.core.stripe

import androidx.activity.ComponentActivity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class StripePaymentSheetHelper {
    
    private var currentContinuation: kotlin.coroutines.Continuation<PaymentSheetResult>? = null
    
    fun initialize(publishableKey: String, activity: ComponentActivity) {
        PaymentConfiguration.init(activity, publishableKey)
    }
    
    suspend fun presentPaymentSheet(
        activity: ComponentActivity,
        clientSecret: String,
        customerId: String? = null,
        ephemeralKeySecret: String? = null
    ): PaymentSheetResult = suspendCancellableCoroutine { continuation ->
        currentContinuation = continuation
        
        val configurationBuilder = PaymentSheet.Configuration.Builder("All In Connect")
        // Only configure customer if we have both customerId and ephemeralKeySecret
        if (customerId != null && ephemeralKeySecret != null) {
            val customerConfig = PaymentSheet.CustomerConfiguration(
                id = customerId,
                ephemeralKeySecret = ephemeralKeySecret
            )
            configurationBuilder.customer(customerConfig)
        }
        val configuration = configurationBuilder.build()
        
        val paymentSheet = PaymentSheet(activity) { result ->
            currentContinuation?.resume(result)
            currentContinuation = null
        }
        
        paymentSheet.presentWithPaymentIntent(
            paymentIntentClientSecret = clientSecret,
            configuration = configuration
        )
    }
}
