package com.allinconnect.app.data.dto.profile

import com.allinconnect.app.data.dto.offer.OfferCategory
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val address: String? = null,
    val city: String? = null,
    val postalCode: String? = null,
    val birthDate: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val establishmentName: String? = null,
    val establishmentDescription: String? = null,
    val phoneNumber: String? = null,
    val website: String? = null,
    val instagram: String? = null,
    val openingHours: String? = null,
    val profession: String? = null,
    val category: OfferCategory? = null,
    val subCategory: String? = null,
    val club10: Boolean? = null
)

@Serializable
data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String
)

@Serializable
data class UserMeResponse(
    val id: Int,
    val email: String,
    val firstName: String?,
    val lastName: String?,
    val userType: String,
    val subscriptionType: String?,
    val address: String?,
    val city: String?,
    val postalCode: String?,
    val birthDate: String?,
    val establishmentName: String?,
    val establishmentDescription: String?,
    val phoneNumber: String?,
    val website: String?,
    val instagram: String?,
    val openingHours: String?,
    val profession: String?,
    val category: OfferCategory?,
    val subCategory: String?,
    val referralCode: String?,
    val premiumEnabled: Boolean? = null,
    val card: CardResponse? = null,
    val isCardActive: Boolean? = null
)

@Serializable
data class UserLightResponse(
    val firstName: String,
    val lastName: String,
    val isMember: Boolean? = null,
    val userType: String? = null,
    val isCardActive: Boolean? = null,
    val referralCount: Int? = null,
    val favoriteCount: Int? = null,
    val subscriptionDate: String? = null,
    val renewalDate: String? = null,
    val subscriptionAmount: Double? = null,
    val walletBalance: Double? = null,
    val referralCode: String? = null,
    val planDuration: String? = null
)

@Serializable
data class CardMember(
    val id: Int,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null
)

@Serializable
data class CardResponse(
    val id: Int? = null,
    val cardNumber: String,
    val type: String,
    val members: List<CardMember>? = null,
    val invitedEmails: List<String>? = null,
    val ownerId: Int? = null,
    val ownerName: String? = null
)
