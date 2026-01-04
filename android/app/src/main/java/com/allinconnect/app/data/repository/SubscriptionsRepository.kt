package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.SubscriptionsApi
import com.allinconnect.app.data.dto.subscription.*
import com.allinconnect.app.data.dto.profile.PaymentResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscriptionsRepository @Inject constructor(
    private val subscriptionsApi: SubscriptionsApi
) {
    suspend fun getPlans(): Result<List<SubscriptionPlanResponse>> {
        return try {
            val response = subscriptionsApi.getPlans()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun subscribe(planId: Int): Result<Unit> {
        return try {
            subscriptionsApi.subscribe(planId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getMyPayments(): Result<List<PaymentResponse>> {
        return try {
            val response = subscriptionsApi.getMyPayments()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun inviteFamilyMember(email: String): Result<Unit> {
        return try {
            val request = InviteRequest(email = email)
            subscriptionsApi.inviteFamilyMember(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun removeFamilyMember(memberId: Int? = null, email: String? = null): Result<Unit> {
        return try {
            val request = RemoveMemberRequest(memberId = memberId, email = email)
            subscriptionsApi.removeFamilyMember(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getCardMembers(): Result<CardMembersResponse> {
        return try {
            val response = subscriptionsApi.getCardMembers()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getCardOwner(): Result<CardOwnerResponse> {
        return try {
            val response = subscriptionsApi.getCardOwner()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createPaymentIntent(planId: Int): Result<PaymentIntentResponse> {
        return try {
            val request = mapOf("planId" to planId)
            val response = subscriptionsApi.createPaymentIntent(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

