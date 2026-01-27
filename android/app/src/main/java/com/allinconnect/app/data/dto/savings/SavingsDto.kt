package com.allinconnect.app.data.dto.savings

import kotlinx.serialization.Serializable

@Serializable
data class SavingsResponse(
    val id: Int,
    val shopName: String,
    val description: String? = null,
    val amount: Double,
    val date: String
)

@Serializable
data class SavingsRequest(
    val shopName: String,
    val description: String? = null,
    val amount: Double,
    val date: String
)
