package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.FavoritesApi
import com.allinconnect.app.data.dto.partner.PartnerProfessionalResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository @Inject constructor(
    private val favoritesApi: FavoritesApi
) {
    suspend fun getFavorites(): Result<List<PartnerProfessionalResponse>> {
        return try {
            val response = favoritesApi.getFavorites()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun addFavorite(professionalId: Int): Result<Unit> {
        return try {
            favoritesApi.addFavorite(professionalId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun removeFavorite(professionalId: Int): Result<Unit> {
        return try {
            favoritesApi.removeFavorite(professionalId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

