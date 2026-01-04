package com.allinconnect.app.data.dto.billing

import kotlinx.serialization.Serializable

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
data class SubscriptionStatusResponse(
    val premiumEnabled: Boolean,
    val subscriptionStatus: String? = null,
    val currentPeriodEnd: String? = null
)

@Serializable
data class PortalResponse(
    val url: String
)

@Serializable
data class CancelSubscriptionResponse(
    val id: String,
    val status: String? = null,
    val canceledAt: Int? = null
)

