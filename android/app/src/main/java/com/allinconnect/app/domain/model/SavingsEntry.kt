package com.allinconnect.app.domain.model

import java.util.Date

data class SavingsEntry(
    val id: String,
    val apiId: Int? = null,
    val amount: Double,
    val date: Date,
    val store: String,
    val description: String? = null
)

