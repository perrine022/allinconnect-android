package com.allinconnect.app.data.dto.offer

import kotlinx.serialization.Serializable

@Serializable
enum class OfferCategory {
    SANTE_BIEN_ETRE,
    BEAUTE_ESTHETIQUE,
    FOOD_PLAISIRS,
    LOISIRS_DIVERTISSEMENTS,
    SERVICE_PRATIQUES,
    ENTRE_PROS
}

@Serializable
enum class OfferType {
    OFFRE,
    EVENEMENT
}

@Serializable
data class ProfessionalResponse(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val city: String? = null,
    val profession: String? = null,
    val category: OfferCategory? = null
)

@Serializable
data class OfferResponse(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val featured: Boolean? = null,
    val status: String? = null,
    val professional: ProfessionalResponse? = null,
    val type: String? = null, // "OFFRE" or "EVENEMENT"
    val imageUrl: String? = null
)

