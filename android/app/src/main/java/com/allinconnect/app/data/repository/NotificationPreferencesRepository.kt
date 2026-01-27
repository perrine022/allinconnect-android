package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.NotificationPreferencesApi
import com.allinconnect.app.data.dto.notification.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationPreferencesRepository @Inject constructor(
    private val notificationPreferencesApi: NotificationPreferencesApi
) {
    suspend fun getPreferences(): Result<NotificationPreferencesResponse> {
        return try {
            Result.success(notificationPreferencesApi.getPreferences())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun updatePreferences(request: NotificationPreferencesRequest): Result<NotificationPreferencesResponse> {
        return try {
            Result.success(notificationPreferencesApi.updatePreferences(request))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}
