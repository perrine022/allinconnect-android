package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.payment.*
import retrofit2.http.*

interface PaymentApi {
    @GET("/payment/public-key")
    suspend fun getPublicKey(): String
    
    @POST("/payment/payment-sheet")
    suspend fun createPaymentSheet(@Body request: PaymentSheetRequest): PaymentSheetResponse
    
    @POST("/payment/create-payment-intent")
    suspend fun createPaymentIntent(@Body request: CreatePaymentIntentRequest): CreatePaymentIntentResponse
    
    @POST("/payment/create-customer")
    suspend fun createCustomer(): CreateCustomerResponse
    
    @GET("/payment/status/{paymentIntentId}")
    suspend fun getPaymentStatus(@Path("paymentIntentId") paymentIntentId: String): PaymentStatusResponse
}

