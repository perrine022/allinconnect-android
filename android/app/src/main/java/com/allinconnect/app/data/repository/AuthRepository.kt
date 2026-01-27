package com.allinconnect.app.data.repository

import com.allinconnect.app.core.auth.AuthTokenManager
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.AuthApi
import com.allinconnect.app.data.dto.auth.AuthResponse
import com.allinconnect.app.data.dto.auth.ForgotPasswordRequest
import com.allinconnect.app.data.dto.auth.LoginRequest
import com.allinconnect.app.data.dto.auth.RegistrationRequest
import com.allinconnect.app.data.dto.auth.ResetPasswordRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val authTokenManager: AuthTokenManager
) {
    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val request = LoginRequest(email, password)
            val response = authApi.login(request)
            authTokenManager.saveToken(response.token)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun register(request: RegistrationRequest): Result<AuthResponse> {
        return try {
            val response = authApi.register(request)
            authTokenManager.saveToken(response.token)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun forgotPassword(email: String): Result<Unit> {
        return try {
            val request = ForgotPasswordRequest(email)
            authApi.forgotPassword(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun resetPassword(token: String, newPassword: String): Result<Unit> {
        return try {
            val request = ResetPasswordRequest(token, newPassword)
            authApi.resetPassword(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun logout() {
        authTokenManager.removeToken()
    }
}
