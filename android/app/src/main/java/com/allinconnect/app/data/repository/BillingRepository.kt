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
    suspend fun initPaymentSheet(request: Map<String, Any>): Result<PaymentSheetInitResponse> {
        return try {
            Result.success(billingApi.initPaymentSheet(request))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getSubscriptionStatus(): Result<SubscriptionStatusResponse> {
        return try {
            Result.success(billingApi.getSubscriptionStatus())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createPortalSession(): Result<PortalResponse> {
        return try {
            Result.success(billingApi.createPortalSession())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun cancelSubscription(): Result<Unit> {
        return try {
            billingApi.cancelSubscription()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}
