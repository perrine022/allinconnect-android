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
    val apiId: Int? = null
) {
    fun fullImageUrl(baseUrl: String): String? {
        if (imageUrl.isNullOrEmpty()) return null
        
        if (imageUrl.startsWith("http://") || imageUrl.startsWith("https://")) {
            return imageUrl
        }
        
        val base = baseUrl.replace("/api/v1", "")
        val path = if (imageUrl.startsWith("/")) imageUrl else "/$imageUrl"
        return "$base$path"
    }
}

