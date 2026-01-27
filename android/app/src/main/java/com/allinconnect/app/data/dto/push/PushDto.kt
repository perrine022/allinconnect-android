package com.allinconnect.app.data.dto.push

import kotlinx.serialization.Serializable

@Serializable
data class RegisterTokenRequest(
    val token: String
)
