package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.rating.*
import retrofit2.http.*

interface RatingsApi {
    @POST("ratings")
    suspend fun createRating(@Body request: RatingRequest): RatingResponse
    
    @GET("ratings/{professionalId}")
    suspend fun getRatings(@Path("professionalId") professionalId: Int): List<RatingResponse>
}
