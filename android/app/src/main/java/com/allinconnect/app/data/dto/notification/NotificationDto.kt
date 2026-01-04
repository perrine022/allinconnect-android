package com.allinconnect.app.data.dto.notification

import kotlinx.serialization.Serializable

@Serializable
data class NotificationPreferencesResponse(
    val notifyNewOffers: Boolean,
    val notifyNewProNearby: Boolean,
    val notifyLocalEvents: Boolean,
    val notificationRadius: Int,
    val preferredCategories: List<String>
)

@Serializable
data class NotificationPreferencesRequest(
    val notifyNewOffers: Boolean,
    val notifyNewProNearby: Boolean,
    val notifyLocalEvents: Boolean,
    val notificationRadius: Int,
    val preferredCategories: List<String>
)

