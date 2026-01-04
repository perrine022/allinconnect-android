package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.offer.OfferCategory
import com.allinconnect.app.data.dto.partner.PartnerProfessionalResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PartnersApi {
    @GET("/users/professionals")
    suspend fun getAllProfessionals(): List<PartnerProfessionalResponse>
    
    @GET("/users/professionals/by-city")
    suspend fun getProfessionalsByCity(@Query("city") city: String): List<PartnerProfessionalResponse>
    
    @GET("/users/professionals/search")
    suspend fun searchProfessionals(
        @Query("city") city: String? = null,
        @Query("category") category: String? = null,
        @Query("name") name: String? = null,
        @Query("lat") latitude: Double? = null,
        @Query("lon") longitude: Double? = null,
        @Query("radius") radius: Double? = null
    ): List<PartnerProfessionalResponse>
    
    @GET("/users/{id}")
    suspend fun getProfessionalById(@Path("id") id: Int): PartnerProfessionalResponse
}

