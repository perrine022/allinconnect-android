package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.subscription.*
import com.allinconnect.app.data.dto.profile.PaymentResponse
import retrofit2.http.*

interface SubscriptionsApi {
    @GET("/subscriptions/plans")
    suspend fun getPlans(): List<SubscriptionPlanResponse>
    
    @POST("/subscriptions/subscribe/{planId}")
    suspend fun subscribe(@Path("planId") planId: Int)
    
    @GET("/subscriptions/my-payments")
    suspend fun getMyPayments(): List<PaymentResponse>
    
    @POST("/cards/invite")
    suspend fun inviteFamilyMember(@Body request: InviteRequest)
    
    @POST("/cards/remove-member")
    suspend fun removeFamilyMember(@Body request: RemoveMemberRequest)
    
    @GET("/cards/members")
    suspend fun getCardMembers(): CardMembersResponse
    
    @GET("/cards/owner")
    suspend fun getCardOwner(): CardOwnerResponse
    
    @GET("/cards/family/emails")
    suspend fun getFamilyCardEmails(): FamilyCardEmailsResponse
    
    @PUT("/cards/family/emails")
    suspend fun updateFamilyCardEmails(@Body request: UpdateFamilyCardEmailsRequest)
    
    @POST("/subscriptions/create-payment-intent")
    suspend fun createPaymentIntent(@Body request: Map<String, Int>): PaymentIntentResponse
}

