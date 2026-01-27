package com.allinconnect.app.data.dto.subscription

import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionPlanResponse(
    val id: Int,
    val title: String,
    val description: String? = null,
    val price: Double,
    val category: String? = null,
    val duration: String? = null,
    val stripePriceId: String? = null,
    val stripeProductId: String? = null
)

@Serializable
data class CardMembersResponse(
    val activeMembers: List<CardMember>,
    val pendingInvitations: List<String>
)

@Serializable
data class CardMember(
    val id: Int,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null
)

@Serializable
data class PaymentIntentResponse(
    val paymentIntent: String,
    val customerId: String,
    val ephemeralKey: String,
    val publishableKey: String
)
