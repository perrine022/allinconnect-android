package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.SavingsApi
import com.allinconnect.app.data.dto.savings.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavingsRepository @Inject constructor(
    private val savingsApi: SavingsApi
) {
    suspend fun getSavings(): Result<List<SavingsResponse>> {
        return try {
            val response = savingsApi.getSavings()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createSavings(request: SavingsRequest): Result<SavingsResponse> {
        return try {
            val response = savingsApi.createSavings(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun updateSavings(id: Int, request: SavingsRequest): Result<SavingsResponse> {
        return try {
            val response = savingsApi.updateSavings(id, request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun deleteSavings(id: Int): Result<Unit> {
        return try {
            savingsApi.deleteSavings(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

