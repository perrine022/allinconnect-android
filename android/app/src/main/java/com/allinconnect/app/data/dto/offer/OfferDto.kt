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
    val reduction: String? = null,
    val discount: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val featured: Boolean? = null,
    val status: String? = null,
    val professional: ProfessionalResponse? = null,
    val professionalId: Int? = null,
    val professionalName: String? = null,
    val city: String? = null,
    val category: String? = null,
    val type: String? = null,
    val imageUrl: String? = null,
    val distanceMeters: Double? = null
)
