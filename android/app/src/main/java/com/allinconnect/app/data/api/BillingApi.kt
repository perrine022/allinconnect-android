package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.billing.*
import retrofit2.http.*

interface BillingApi {
    @POST("/billing/subscription/payment-sheet")
    suspend fun createSubscriptionPaymentSheet(@Body request: Map<String, String>): SubscriptionPaymentSheetResponse
    
    @GET("/billing/subscription/status")
    suspend fun getSubscriptionStatus(): SubscriptionStatusResponse
    
    @POST("/billing/portal")
    suspend fun createPortalSession(): PortalResponse
    
    @POST("/billing/subscription/cancel")
    suspend fun cancelSubscription(@Body request: Map<String, String>): CancelSubscriptionResponse
}

