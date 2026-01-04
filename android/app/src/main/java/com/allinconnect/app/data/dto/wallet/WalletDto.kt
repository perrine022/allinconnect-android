package com.allinconnect.app.data.dto.wallet

import kotlinx.serialization.Serializable

@Serializable
data class WalletHistoryResponse(
    val id: Int,
    val amount: Double,
    val description: String,
    val date: String,
    val user: WalletUserResponse? = null
)

@Serializable
data class WalletUserResponse(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null
)

@Serializable
data class WalletRequestResponse(
    val id: Int,
    val totalAmount: Double,
    val status: String,
    val createdAt: String,
    val professionals: String
)

@Serializable
data class WalletRequest(
    val amount: Double,
    val professionals: String
)

