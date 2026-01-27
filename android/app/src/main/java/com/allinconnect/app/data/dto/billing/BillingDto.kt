package com.allinconnect.app.data.dto.billing

import kotlinx.serialization.Serializable

@Serializable
data class PaymentSheetInitResponse(
    val paymentIntentClientSecret: String,
    val customerId: String,
    val ephemeralKeySecret: String,
    val publishableKey: String
)

@Serializable
data class SubscriptionStatusResponse(
    val premiumEnabled: Boolean,
    val subscriptionStatus: String? = null,
    val currentPeriodEnd: String? = null
)

@Serializable
data class SubscriptionPaymentSheetResponse(
    val paymentIntent: String,
    val customerId: String,
    val ephemeralKey: String,
    val publishableKey: String,
    val subscriptionId: String? = null,
    val intentType: String? = null
)

@Serializable
data class PortalResponse(
    val url: String
)
