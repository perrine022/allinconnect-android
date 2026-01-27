package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.partner.PartnerProfessionalResponse
import retrofit2.http.*

interface PartnersApi {
    @GET("users/professionals")
    suspend fun getAllProfessionals(
        @Query("city") city: String? = null,
        @Query("category") category: String? = null,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null,
        @Query("radius") radius: Double? = null,
        @Query("club10") club10: Boolean? = null
    ): List<PartnerProfessionalResponse>
    
    @GET("users/professionals/{id}")
    suspend fun getProfessionalById(@Path("id") id: Int): PartnerProfessionalResponse
    
    @GET("users/professionals/search")
    suspend fun searchProfessionals(
        @Query("query") query: String? = null,
        @Query("city") city: String? = null,
        @Query("category") category: String? = null
    ): List<PartnerProfessionalResponse>
}
