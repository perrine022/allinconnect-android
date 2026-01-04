package com.allinconnect.app.data.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegistrationRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val address: String? = null,
    val city: String? = null,
    val birthDate: String? = null,
    val userType: String, // "CLIENT" or "PROFESSIONAL"
    val subscriptionType: String, // "FREE" or "PREMIUM"
    val profession: String? = null,
    val category: String? = null,
    val referralCode: String? = null
)

@Serializable
data class AuthResponse(
    val token: String
)

@Serializable
data class ForgotPasswordRequest(
    val email: String
)

@Serializable
data class ResetPasswordRequest(
    val token: String,
    val newPassword: String
)

