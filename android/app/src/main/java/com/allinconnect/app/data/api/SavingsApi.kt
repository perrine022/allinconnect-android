package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.savings.*
import retrofit2.http.*

interface SavingsApi {
    @GET("savings")
    suspend fun getSavings(): List<SavingsResponse>
    
    @POST("savings")
    suspend fun createSavings(@Body request: SavingsRequest): SavingsResponse
    
    @PUT("savings/{id}")
    suspend fun updateSavings(@Path("id") id: Int, @Body request: SavingsRequest): SavingsResponse
    
    @DELETE("savings/{id}")
    suspend fun deleteSavings(@Path("id") id: Int): Unit
}
