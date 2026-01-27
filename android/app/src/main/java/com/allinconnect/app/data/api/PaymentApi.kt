package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.payment.*
import retrofit2.http.*

interface PaymentApi {
    @POST("payment/payment-sheet")
    suspend fun createPaymentSheet(@Body request: PaymentSheetRequest): PaymentSheetResponse
    
    @POST("payment/payment-intent")
    suspend fun createPaymentIntent(@Body request: CreatePaymentIntentRequest): PaymentSheetResponse
}
