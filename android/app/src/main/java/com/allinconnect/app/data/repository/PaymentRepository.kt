package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.PaymentApi
import com.allinconnect.app.data.dto.payment.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor(
    private val paymentApi: PaymentApi
) {
    suspend fun getPublicKey(): Result<String> {
        return try {
            val response = paymentApi.getPublicKey()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createPaymentSheet(request: PaymentSheetRequest): Result<PaymentSheetResponse> {
        return try {
            val response = paymentApi.createPaymentSheet(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createPaymentIntent(request: CreatePaymentIntentRequest): Result<CreatePaymentIntentResponse> {
        return try {
            val response = paymentApi.createPaymentIntent(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createCustomer(): Result<CreateCustomerResponse> {
        return try {
            val response = paymentApi.createCustomer()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getPaymentStatus(paymentIntentId: String): Result<PaymentStatusResponse> {
        return try {
            val response = paymentApi.getPaymentStatus(paymentIntentId)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

