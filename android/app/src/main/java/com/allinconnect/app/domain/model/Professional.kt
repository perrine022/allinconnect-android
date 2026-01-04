package com.allinconnect.app.domain.model

data class Professional(
    val id: String,
    val firstName: String,
    val lastName: String,
    val profession: String,
    val category: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val phone: String? = null,
    val email: String? = null,
    val profileImageName: String,
    val establishmentImageUrl: String? = null,
    val websiteURL: String? = null,
    val instagramURL: String? = null,
    val description: String? = null,
    val isFavorite: Boolean = false
) {
    val fullName: String
        get() = "$firstName $lastName"
}

