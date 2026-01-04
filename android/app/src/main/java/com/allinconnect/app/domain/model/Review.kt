package com.allinconnect.app.domain.model

import java.util.Date

data class Review(
    val id: String,
    val userName: String,
    val rating: Double,
    val comment: String,
    val date: Date
)

