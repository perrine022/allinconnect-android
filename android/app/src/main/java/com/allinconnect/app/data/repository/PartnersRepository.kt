package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.PartnersApi
import com.allinconnect.app.data.dto.partner.PartnerProfessionalResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartnersRepository @Inject constructor(
    private val partnersApi: PartnersApi
) {
    suspend fun getAllProfessionals(): Result<List<PartnerProfessionalResponse>> {
        return try {
            val response = partnersApi.getAllProfessionals()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getProfessionalsByCity(city: String): Result<List<PartnerProfessionalResponse>> {
        return try {
            val response = partnersApi.getProfessionalsByCity(city)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun searchProfessionals(
        city: String? = null,
        category: String? = null,
        name: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        radius: Double? = null
    ): Result<List<PartnerProfessionalResponse>> {
        return try {
            val response = partnersApi.searchProfessionals(city, category, name, latitude, longitude, radius)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getProfessionalById(id: Int): Result<PartnerProfessionalResponse> {
        return try {
            val response = partnersApi.getProfessionalById(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

