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
    val category: String? = null
)

@Serializable
data class ChangePasswordRequest(
    val currentPassword: String,
    val newPassword: String,
    val confirmationPassword: String
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

@Serializable
data class UserMeResponse(
    val id: Int? = null,
    val email: String? = null,
    val firstName: String = "",
    val lastName: String = "",
    val address: String? = null,
    val city: String? = null,
    val postalCode: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val card: CardResponse? = null,
    val isCardActive: Boolean? = null,
    val referralCode: String? = null,
    val premiumEnabled: Boolean? = null,
    val subscriptionType: String? = null,
    val establishmentName: String? = null,
    val establishmentDescription: String? = null,
    val establishmentImageUrl: String? = null,
    val phoneNumber: String? = null,
    val website: String? = null,
    val instagram: String? = null,
    val openingHours: String? = null,
    val profession: String? = null,
    val category: String? = null
)

@Serializable
data class UserLightResponse(
    val firstName: String = "",
    val lastName: String = "",
    val isMember: Boolean? = null,
    val card: CardResponse? = null,
    val isCardActive: Boolean? = null,
    val referralCount: Int? = null,
    val favoriteCount: Int? = null,
    val subscriptionDate: String? = null,
    val renewalDate: String? = null,
    val subscriptionAmount: Double? = null,
    val payments: List<PaymentResponse>? = null,
    val walletBalance: Double? = null,
    val referralCode: String? = null,
    val notificationPreference: NotificationPreferencesResponse? = null
)

@Serializable
data class PaymentResponse(
    val id: Int,
    val amount: Double,
    val paymentDate: String,
    val status: String? = null
)

