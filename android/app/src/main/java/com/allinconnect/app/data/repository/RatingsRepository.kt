package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.RatingsApi
import com.allinconnect.app.data.dto.rating.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatingsRepository @Inject constructor(
    private val ratingsApi: RatingsApi
) {
    suspend fun createRating(ratedId: Int, score: Int, comment: String?): Result<RatingResponse> {
        return try {
            val request = RatingRequest(ratedId = ratedId, score = score, comment = comment)
            val response = ratingsApi.createRating(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getRatingsByUser(userId: Int): Result<List<RatingResponse>> {
        return try {
            val response = ratingsApi.getRatingsByUser(userId)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getAverageRating(userId: Int): Result<Double> {
        return try {
            val response = ratingsApi.getAverageRating(userId)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

