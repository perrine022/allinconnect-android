package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.PartnersApi
import com.allinconnect.app.data.mapper.PartnerMapper
import com.allinconnect.app.domain.model.Partner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartnersRepository @Inject constructor(
    private val partnersApi: PartnersApi
) {
    suspend fun getAllProfessionals(
        city: String? = null,
        category: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        radius: Double? = null,
        club10: Boolean? = null
    ): Result<List<Partner>> {
        return try {
            val response = partnersApi.getAllProfessionals(
                city = city,
                category = category,
                latitude = latitude,
                longitude = longitude,
                radius = radius,
                club10 = club10
            )
            Result.success(response.map { PartnerMapper.toDomain(it) })
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getProfessionalById(id: Int): Result<Partner> {
        return try {
            val response = partnersApi.getProfessionalById(id)
            Result.success(PartnerMapper.toDomain(response))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun searchProfessionals(
        query: String? = null,
        city: String? = null,
        category: String? = null
    ): Result<List<Partner>> {
        return try {
            val response = partnersApi.searchProfessionals(
                query = query,
                city = city,
                category = category
            )
            Result.success(response.map { PartnerMapper.toDomain(it) })
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}
