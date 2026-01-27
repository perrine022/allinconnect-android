package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.SubscriptionsApi
import com.allinconnect.app.data.dto.subscription.*
import com.allinconnect.app.data.dto.billing.SubscriptionPaymentSheetResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscriptionsRepository @Inject constructor(
    private val subscriptionsApi: SubscriptionsApi
) {
    suspend fun getPlans(): Result<List<SubscriptionPlanResponse>> {
        return try {
            Result.success(subscriptionsApi.getPlans())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun subscribe(request: Map<String, Any>): Result<SubscriptionPaymentSheetResponse> {
        return try {
            Result.success(subscriptionsApi.subscribe(request))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getPaymentHistory(): Result<List<PaymentIntentResponse>> {
        return try {
            Result.success(subscriptionsApi.getPaymentHistory())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getCardMembers(): Result<CardMembersResponse> {
        return try {
            Result.success(subscriptionsApi.getCardMembers())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}
