package com.allinconnect.app.domain.model

import java.time.Instant

data class Review(
    val id: String,
    val userName: String,
    val rating: Double,
    val comment: String,
    val date: Instant
)
