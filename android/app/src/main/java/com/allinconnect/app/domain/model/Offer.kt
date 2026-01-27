package com.allinconnect.app.domain.model

enum class OfferType {
    OFFER,
    EVENT
}

data class Offer(
    val id: String,
    val title: String,
    val description: String,
    val businessName: String,
    val validUntil: String,
    val startDate: String? = null,
    val discount: String,
    val imageName: String,
    val imageUrl: String? = null,
    val offerType: OfferType = OfferType.OFFER,
    val isClub10: Boolean = false,
    val partnerId: String? = null,
    val apiId: Int? = null,
    val distanceMeters: Double? = null
)
