package com.allinconnect.app.data.dto.subscription

import com.allinconnect.app.data.dto.profile.CardMember
import com.allinconnect.app.data.dto.profile.PaymentResponse
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
data class CardOwnerResponse(
    val isOwner: Boolean
)

@Serializable
data class FamilyCardEmailsResponse(
    val cardId: Int,
    val ownerEmail: String,
    val emails: List<String>,
    val isOwner: Boolean
)

@Serializable
data class UpdateFamilyCardEmailsRequest(
    val emails: List<String>
)

@Serializable
data class PaymentIntentResponse(
    val clientSecret: String,
    val amount: Double,
    val currency: String
)

@Serializable
data class InviteRequest(
    val email: String
)

@Serializable
data class RemoveMemberRequest(
    val memberId: Int? = null,
    val email: String? = null
)

