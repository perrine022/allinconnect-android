package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.notification.*
import retrofit2.http.*

interface NotificationPreferencesApi {
    @GET("notification-preferences")
    suspend fun getPreferences(): NotificationPreferencesResponse
    
    @PUT("notification-preferences")
    suspend fun updatePreferences(@Body request: NotificationPreferencesRequest): NotificationPreferencesResponse
}
