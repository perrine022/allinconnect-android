package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.offer.OfferCategory
import com.allinconnect.app.data.dto.offer.OfferResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.Query

interface OffersApi {
    @GET("/offers")
    suspend fun getAllOffers(
        @Query("city") city: String? = null,
        @Query("category") category: String? = null,
        @Query("professionalId") professionalId: Int? = null,
        @Query("type") type: String? = null,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<OfferResponse>
    
    @GET("/offers/active")
    suspend fun getActiveOffers(
        @Query("city") city: String? = null,
        @Query("category") category: String? = null,
        @Query("type") type: String? = null,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<OfferResponse>
    
    @GET("/offers/{id}")
    suspend fun getOfferDetail(@Path("id") id: Int): OfferResponse
    
    @GET("/offers/my-offers")
    suspend fun getMyOffers(): List<OfferResponse>
    
    @GET("/offers/professional/{professionalId}")
    suspend fun getOffersByProfessional(@Path("professionalId") professionalId: Int): List<OfferResponse>
    
    @GET("/offers/professional/{professionalId}/active")
    suspend fun getActiveOffersByProfessional(@Path("professionalId") professionalId: Int): List<OfferResponse>
    
    @POST("/offers")
    suspend fun createOffer(@Body offer: OfferResponse): OfferResponse
    
    @PUT("/offers/{id}")
    suspend fun updateOffer(@Path("id") id: Int, @Body offer: OfferResponse): OfferResponse
    
    @POST("/offers/{id}/archive")
    suspend fun archiveOffer(@Path("id") id: Int): OfferResponse
    
    @DELETE("/offers/{id}")
    suspend fun deleteOffer(@Path("id") id: Int)
}

