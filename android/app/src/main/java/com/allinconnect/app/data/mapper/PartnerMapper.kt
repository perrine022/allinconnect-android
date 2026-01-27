package com.allinconnect.app.data.mapper

import com.allinconnect.app.data.dto.partner.PartnerProfessionalResponse
import com.allinconnect.app.domain.model.Partner
import java.util.*

object PartnerMapper {
    fun toDomain(dto: PartnerProfessionalResponse): Partner {
        val category = dto.category?.name?.replace("_", " ")?.lowercase()?.capitalize() 
            ?: dto.profession ?: "Autre"
        
        return Partner(
            id = dto.id.toString(),
            name = dto.establishmentName ?: "${dto.firstName} ${dto.lastName}",
            category = category,
            subCategory = dto.subCategory,
            address = dto.address ?: "",
            city = dto.city ?: "",
            postalCode = "",
            phone = dto.phoneNumber,
            email = dto.email,
            website = dto.website,
            instagram = dto.instagram,
            description = dto.establishmentDescription,
            rating = dto.averageRating ?: 0.0,
            reviewCount = dto.reviewCount ?: 0,
            discount = if (dto.club10 == true) 10 else null,
            imageName = "person.circle.fill",
            headerImageName = "person.circle.fill",
            establishmentImageUrl = dto.establishmentImageUrl,
            isFavorite = false,
            apiId = dto.id,
            distanceMeters = dto.distanceMeters
        )
    }
}
