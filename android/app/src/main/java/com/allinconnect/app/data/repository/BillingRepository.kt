package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.BillingApi
import com.allinconnect.app.data.dto.billing.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BillingRepository @Inject constructor(
    private val billingApi: BillingApi
) {
    suspend fun createSubscriptionPaymentSheet(priceId: String): Result<SubscriptionPaymentSheetResponse> {
        return try {
            val request = mapOf("priceId" to priceId)
            val response = billingApi.createSubscriptionPaymentSheet(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getSubscriptionStatus(): Result<SubscriptionStatusResponse> {
        return try {
            val response = billingApi.getSubscriptionStatus()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createPortalSession(): Result<PortalResponse> {
        return try {
            val response = billingApi.createPortalSession()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun cancelSubscription(subscriptionId: String): Result<CancelSubscriptionResponse> {
        return try {
            val request = mapOf("subscriptionId" to subscriptionId)
            val response = billingApi.cancelSubscription(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

