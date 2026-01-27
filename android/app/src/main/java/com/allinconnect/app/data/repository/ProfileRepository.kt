package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.ProfileApi
import com.allinconnect.app.data.dto.profile.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val profileApi: ProfileApi
) {
    suspend fun getUserMe(): Result<UserMeResponse> {
        return try {
            Result.success(profileApi.getUserMe())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getUserLight(): Result<UserLightResponse> {
        return try {
            Result.success(profileApi.getUserLight())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun updateProfile(request: UpdateProfileRequest): Result<UserMeResponse> {
        return try {
            Result.success(profileApi.updateProfile(request))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun changePassword(oldPassword: String, newPassword: String): Result<Unit> {
        return try {
            profileApi.changePassword(ChangePasswordRequest(oldPassword, newPassword))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getCard(): Result<CardResponse> {
        return try {
            Result.success(profileApi.getCard())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}
