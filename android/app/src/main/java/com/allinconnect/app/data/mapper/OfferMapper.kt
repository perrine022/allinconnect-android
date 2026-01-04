package com.allinconnect.app.data.mapper

import com.allinconnect.app.core.config.ApiConfig
import com.allinconnect.app.data.dto.offer.OfferResponse
import com.allinconnect.app.domain.model.Offer
import com.allinconnect.app.domain.model.OfferType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

object OfferMapper {
    
    fun OfferResponse.toDomain(): Offer {
        val offerUUID = UUID.nameUUIDFromBytes(id.toString().toByteArray()).toString()
        
        val businessName = professional?.let { prof ->
            if (prof.firstName != null && prof.lastName != null) {
                "${prof.firstName} ${prof.lastName}"
            } else {
                prof.firstName ?: "Entreprise"
            }
        } ?: "Entreprise"
        
        val validUntil = formatDateToFrench(endDate) ?: endDate ?: "N/A"
        val startDateFormatted = formatDateToFrench(startDate)
        
        val discount = price?.let { String.format(Locale.FRANCE, "%.2f€", it) } ?: "Sur devis"
        
        val offerTypeEnum = when (type?.uppercase()) {
            "EVENEMENT" -> OfferType.EVENT
            else -> OfferType.OFFER
        }
        
        val isClub10 = featured ?: false
        
        val partnerId = professional?.id?.let { profId ->
            UUID.nameUUIDFromBytes(profId.toString().toByteArray()).toString()
        }
        
        val defaultImage = "tag.fill" // Default SF Symbol equivalent
        
        return Offer(
            id = offerUUID,
            title = title,
            description = description,
            businessName = businessName,
            validUntil = validUntil,
            startDate = startDateFormatted,
            discount = discount,
            imageName = defaultImage,
            imageUrl = imageUrl,
            offerType = offerTypeEnum,
            isClub10 = isClub10,
            partnerId = partnerId,
            apiId = id
        )
    }
    
    private fun formatDateToFrench(dateString: String?): String? {
        if (dateString == null) return null
        
        val formats = listOf(
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd"
        )
        
        for (format in formats) {
            try {
                val parser = SimpleDateFormat(format, Locale.getDefault())
                parser.timeZone = java.util.TimeZone.getTimeZone("UTC")
                val date = parser.parse(dateString)
                if (date != null) {
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
                    return formatter.format(date)
                }
            } catch (e: Exception) {
                // Try next format
            }
        }
        
        return null
    }
}

