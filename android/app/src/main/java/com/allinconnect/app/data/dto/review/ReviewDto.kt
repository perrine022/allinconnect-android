package com.allinconnect.app.data.dto.review

import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    val id: Int,
    val userName: String,
    val rating: Double,
    val comment: String,
    val date: String
)

