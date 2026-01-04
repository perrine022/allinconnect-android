package com.allinconnect.app.domain.model

data class Partner(
    val id: String,
    val name: String,
    val category: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val phone: String? = null,
    val email: String? = null,
    val website: String? = null,
    val instagram: String? = null,
    val description: String? = null,
    val rating: Double,
    val reviewCount: Int,
    val discount: Int? = null,
    val imageName: String,
    val headerImageName: String,
    val establishmentImageUrl: String? = null,
    val isFavorite: Boolean = false,
    val apiId: Int? = null
)

