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
    suspend fun createPaymentSheet(request: PaymentSheetRequest): Result<PaymentSheetResponse> {
        return try {
            Result.success(paymentApi.createPaymentSheet(request))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createPaymentIntent(request: CreatePaymentIntentRequest): Result<PaymentSheetResponse> {
        return try {
            Result.success(paymentApi.createPaymentIntent(request))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}
