package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.partner.PartnerProfessionalResponse
import retrofit2.http.*

interface FavoritesApi {
    @GET("users/favorites")
    suspend fun getFavorites(): List<PartnerProfessionalResponse>
    
    @POST("users/favorites/{professionalId}")
    suspend fun addFavorite(@Path("professionalId") professionalId: Int): Unit
    
    @DELETE("users/favorites/{professionalId}")
    suspend fun removeFavorite(@Path("professionalId") professionalId: Int): Unit
}
