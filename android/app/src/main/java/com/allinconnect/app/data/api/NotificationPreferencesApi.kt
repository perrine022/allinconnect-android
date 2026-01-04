package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.notification.*
import retrofit2.http.*

interface NotificationPreferencesApi {
    @GET("/notification-preferences")
    suspend fun getNotificationPreferences(): NotificationPreferencesResponse
    
    @PUT("/notification-preferences")
    suspend fun updateNotificationPreferences(@Body request: NotificationPreferencesRequest)
}

