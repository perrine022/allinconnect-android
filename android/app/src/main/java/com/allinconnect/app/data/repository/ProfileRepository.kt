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
    suspend fun updateProfile(request: UpdateProfileRequest): Result<Unit> {
        return try {
            profileApi.updateProfile(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun changePassword(request: ChangePasswordRequest): Result<Unit> {
        return try {
            profileApi.changePassword(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getUserLight(): Result<UserLightResponse> {
        return try {
            val response = profileApi.getUserLight()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getUserMe(): Result<UserMeResponse> {
        return try {
            val response = profileApi.getUserMe()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

