package com.allinconnect.app.data.dto.payment

import kotlinx.serialization.Serializable

@Serializable
data class PaymentSheetRequest(
    val amount: Int,
    val currency: String,
    val description: String? = null,
    val captureImmediately: Boolean
)

@Serializable
data class PaymentSheetResponse(
    val paymentIntent: String,
    val ephemeralKey: String,
    val customer: String,
    val publishableKey: String
)

@Serializable
data class CreatePaymentIntentRequest(
    val amount: Int,
    val currency: String,
    val description: String? = null,
    val captureImmediately: Boolean
)
