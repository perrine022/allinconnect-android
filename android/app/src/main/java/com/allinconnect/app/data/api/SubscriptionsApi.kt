package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.subscription.*
import com.allinconnect.app.data.dto.billing.SubscriptionPaymentSheetResponse
import retrofit2.http.*

interface SubscriptionsApi {
    @GET("subscriptions/plans")
    suspend fun getPlans(): List<SubscriptionPlanResponse>
    
    @POST("subscriptions/subscribe")
    suspend fun subscribe(@Body request: Map<String, Any>): SubscriptionPaymentSheetResponse
    
    @GET("subscriptions/payment-history")
    suspend fun getPaymentHistory(): List<PaymentIntentResponse>
    
    @GET("subscriptions/card-members")
    suspend fun getCardMembers(): CardMembersResponse
}
