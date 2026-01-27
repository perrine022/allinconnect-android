package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.offer.OfferResponse
import retrofit2.http.*

interface OffersApi {
    @GET("offers")
    suspend fun getOffers(
        @Query("city") city: String? = null,
        @Query("category") category: String? = null,
        @Query("type") type: String? = null,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null,
        @Query("radius") radius: Double? = null,
        @Query("club10") club10: Boolean? = null
    ): List<OfferResponse>
    
    @GET("offers/{id}")
    suspend fun getOfferById(@Path("id") id: Int): OfferResponse
    
    @POST("offers")
    suspend fun createOffer(@Body offer: Map<String, Any>): OfferResponse
    
    @PUT("offers/{id}")
    suspend fun updateOffer(@Path("id") id: Int, @Body offer: Map<String, Any>): OfferResponse
    
    @DELETE("offers/{id}")
    suspend fun deleteOffer(@Path("id") id: Int): Unit
    
    @PATCH("offers/{id}/archive")
    suspend fun archiveOffer(@Path("id") id: Int): Unit
}
