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
            Result.success(ratingsApi.createRating(RatingRequest(ratedId, score, comment)))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getRatings(professionalId: Int): Result<List<RatingResponse>> {
        return try {
            Result.success(ratingsApi.getRatings(professionalId))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}
