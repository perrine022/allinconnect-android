package com.allinconnect.app.data.dto.rating

import kotlinx.serialization.Serializable

@Serializable
data class RatingRequest(
    val ratedId: Int,
    val score: Int,
    val comment: String? = null
)

@Serializable
data class RaterResponse(
    val firstName: String,
    val lastName: String
)

@Serializable
data class RatingResponse(
    val id: Int,
    val score: Int,
    val comment: String? = null,
    val createdAt: String,
    val rater: RaterResponse? = null
)

