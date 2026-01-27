package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.billing.*
import retrofit2.http.*

interface BillingApi {
    @POST("billing/payment-sheet/init")
    suspend fun initPaymentSheet(@Body request: Map<String, Any>): PaymentSheetInitResponse
    
    @GET("billing/subscription/status")
    suspend fun getSubscriptionStatus(): SubscriptionStatusResponse
    
    @POST("billing/portal/session")
    suspend fun createPortalSession(): PortalResponse
    
    @POST("billing/subscription/cancel")
    suspend fun cancelSubscription(): Unit
}
