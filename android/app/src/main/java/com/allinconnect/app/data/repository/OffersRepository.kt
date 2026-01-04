package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.OffersApi
import com.allinconnect.app.data.dto.offer.OfferCategory
import com.allinconnect.app.data.mapper.OfferMapper
import com.allinconnect.app.domain.model.Offer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OffersRepository @Inject constructor(
    private val offersApi: OffersApi
) {
    suspend fun getAllOffers(
        city: String? = null,
        category: OfferCategory? = null,
        professionalId: Int? = null,
        type: String? = null,
        startDate: String? = null,
        endDate: String? = null
    ): Result<List<Offer>> {
        return try {
            val response = offersApi.getAllOffers(
                city = city,
                category = category?.name,
                professionalId = professionalId,
                type = type,
                startDate = startDate,
                endDate = endDate
            )
            val offers = response.map { it.toDomain() }
            Result.success(offers)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getActiveOffers(
        city: String? = null,
        category: OfferCategory? = null,
        type: String? = null,
        startDate: String? = null,
        endDate: String? = null
    ): Result<List<Offer>> {
        return try {
            val response = offersApi.getActiveOffers(
                city = city,
                category = category?.name,
                type = type,
                startDate = startDate,
                endDate = endDate
            )
            val offers = response.map { it.toDomain() }
            Result.success(offers)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getOfferDetail(id: Int): Result<Offer> {
        return try {
            val response = offersApi.getOfferDetail(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

