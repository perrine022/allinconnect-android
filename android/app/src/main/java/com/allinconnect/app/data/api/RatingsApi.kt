package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.rating.*
import retrofit2.http.*

interface RatingsApi {
    @POST("/ratings")
    suspend fun createRating(@Body request: RatingRequest): RatingResponse
    
    @GET("/ratings/user/{userId}")
    suspend fun getRatingsByUser(@Path("userId") userId: Int): List<RatingResponse>
    
    @GET("/ratings/user/{userId}/average")
    suspend fun getAverageRating(@Path("userId") userId: Int): Double
}

