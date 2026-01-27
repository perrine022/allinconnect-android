package com.allinconnect.app.core.stripe

import android.app.Activity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class StripePaymentSheetHelper {
    
    private var paymentSheet: PaymentSheet? = null
    
    fun initialize(publishableKey: String, activity: Activity) {
        PaymentConfiguration.init(activity, publishableKey)
        paymentSheet = PaymentSheet(activity) { paymentResult ->
            // Result handled in presentPaymentSheet
        }
    }
    
    suspend fun presentPaymentSheet(
        activity: Activity,
        clientSecret: String,
        customerId: String? = null,
        ephemeralKeySecret: String? = null
    ): PaymentSheetResult = suspendCancellableCoroutine { continuation ->
        val configuration = PaymentSheet.Configuration.Builder("All In Connect").apply {
            customerId?.let { setCustomer(it) }
            ephemeralKeySecret?.let { setEphemeralKey(it) }
        }.build()
        
        paymentSheet?.presentWithPaymentIntent(
            clientSecret = clientSecret,
            configuration = configuration,
            callback = { result ->
                continuation.resume(result)
            }
        ) ?: continuation.resume(PaymentSheetResult.Failed(Exception("PaymentSheet not initialized")))
    }
}
