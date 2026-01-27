package com.allinconnect.app.data.mapper

import com.allinconnect.app.data.dto.offer.OfferResponse
import com.allinconnect.app.domain.model.Offer
import com.allinconnect.app.domain.model.OfferType
import java.text.SimpleDateFormat
import java.util.*

object OfferMapper {
    fun toDomain(dto: OfferResponse): Offer {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val displayFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        
        val validUntil = dto.endDate?.let {
            try {
                dateFormat.parse(it)?.let { date -> displayFormat.format(date) }
            } catch (e: Exception) {
                null
            }
        } ?: "N/A"
        
        val startDate = dto.startDate?.let {
            try {
                dateFormat.parse(it)?.let { date -> displayFormat.format(date) }
            } catch (e: Exception) {
                null
            }
        }
        
        val discount = dto.reduction ?: dto.discount ?: "0%"
        val offerType = when (dto.type?.uppercase()) {
            "EVENT", "EVENEMENT" -> OfferType.EVENT
            else -> OfferType.OFFER
        }
        
        return Offer(
            id = dto.id.toString(),
            title = dto.title,
            description = dto.description,
            businessName = dto.professionalName ?: "Partenaire",
            validUntil = validUntil,
            startDate = startDate,
            discount = discount,
            imageName = "tag.fill",
            imageUrl = dto.imageUrl,
            offerType = offerType,
            isClub10 = false, // TODO: Get from API
            partnerId = dto.professionalId?.toString(),
            apiId = dto.id,
            distanceMeters = dto.distanceMeters
        )
    }
}
