package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.OffersApi
import com.allinconnect.app.data.dto.offer.OfferResponse
import com.allinconnect.app.data.mapper.OfferMapper
import com.allinconnect.app.domain.model.Offer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OffersRepository @Inject constructor(
    private val offersApi: OffersApi
) {
    suspend fun getOffers(
        city: String? = null,
        category: String? = null,
        type: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        radius: Double? = null,
        club10: Boolean? = null
    ): Result<List<Offer>> {
        return try {
            val response = offersApi.getOffers(
                city = city,
                category = category,
                type = type,
                startDate = startDate,
                endDate = endDate,
                latitude = latitude,
                longitude = longitude,
                radius = radius,
                club10 = club10
            )
            Result.success(response.map { OfferMapper.toDomain(it) })
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getOfferById(id: Int): Result<Offer> {
        return try {
            val response = offersApi.getOfferById(id)
            Result.success(OfferMapper.toDomain(response))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createOffer(offer: Map<String, Any>): Result<Offer> {
        return try {
            val response = offersApi.createOffer(offer)
            Result.success(OfferMapper.toDomain(response))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun updateOffer(id: Int, offer: Map<String, Any>): Result<Offer> {
        return try {
            val response = offersApi.updateOffer(id, offer)
            Result.success(OfferMapper.toDomain(response))
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun deleteOffer(id: Int): Result<Unit> {
        return try {
            offersApi.deleteOffer(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun archiveOffer(id: Int): Result<Unit> {
        return try {
            offersApi.archiveOffer(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}
