package com.allinconnect.app.domain.model

import java.time.Instant

data class SavingsEntry(
    val id: String,
    val apiId: Int? = null,
    val amount: Double,
    val date: Instant,
    val store: String,
    val description: String? = null
)
