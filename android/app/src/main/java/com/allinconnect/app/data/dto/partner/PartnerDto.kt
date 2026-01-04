package com.allinconnect.app.data.dto.partner

import com.allinconnect.app.data.dto.offer.OfferCategory
import kotlinx.serialization.Serializable

@Serializable
data class PartnerProfessionalResponse(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val address: String? = null,
    val city: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val birthDate: String? = null,
    val userType: String,
    val subscriptionType: String? = null,
    val profession: String? = null,
    val category: String? = null,
    val hasConnectedBefore: Boolean? = null,
    val referralCode: String? = null,
    val subscriptionPlan: SubscriptionPlanResponse? = null,
    val establishmentName: String? = null,
    val establishmentDescription: String? = null,
    val establishmentImageUrl: String? = null,
    val phoneNumber: String? = null,
    val website: String? = null,
    val instagram: String? = null,
    val openingHours: String? = null
)

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

