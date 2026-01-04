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
    suspend fun getNotificationPreferences(): Result<NotificationPreferencesResponse> {
        return try {
            val response = notificationPreferencesApi.getNotificationPreferences()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun updateNotificationPreferences(request: NotificationPreferencesRequest): Result<Unit> {
        return try {
            notificationPreferencesApi.updateNotificationPreferences(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

