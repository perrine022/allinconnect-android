package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.auth.AuthResponse
import com.allinconnect.app.data.dto.auth.ForgotPasswordRequest
import com.allinconnect.app.data.dto.auth.LoginRequest
import com.allinconnect.app.data.dto.auth.RegistrationRequest
import com.allinconnect.app.data.dto.auth.ResetPasswordRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/register")
    suspend fun register(@Body request: RegistrationRequest): AuthResponse
    
    @POST("/auth/authenticate")
    suspend fun authenticate(@Body request: LoginRequest): AuthResponse
    
    @POST("/auth/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest)
    
    @POST("/auth/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest)
}

