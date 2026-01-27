package com.allinconnect.app.core.utils

import com.allinconnect.app.data.dto.offer.OfferCategory

object DefaultImageHelper {
    fun defaultImageForOfferCategory(category: OfferCategory?): String {
        return when (category) {
            OfferCategory.SANTE_BIEN_ETRE -> "figure.strengthtraining.traditional"
            OfferCategory.BEAUTE_ESTHETIQUE -> "sparkles"
            OfferCategory.FOOD_PLAISIRS -> "fork.knife"
            OfferCategory.LOISIRS_DIVERTISSEMENTS -> "gamecontroller.fill"
            OfferCategory.SERVICE_PRATIQUES -> "wrench.and.screwdriver.fill"
            OfferCategory.ENTRE_PROS -> "briefcase.fill"
            null -> "tag.fill"
        }
    }
    
    fun defaultImageForPartnerCategory(category: String): String {
        val categoryLower = category.lowercase()
        
        return when {
            categoryLower.contains("sport") || categoryLower.contains("santé") || 
            categoryLower.contains("bien être") || categoryLower.contains("fitness") -> 
                "figure.strengthtraining.traditional"
            categoryLower.contains("beauté") || categoryLower.contains("esthétique") || 
            categoryLower.contains("spa") || categoryLower.contains("coiffure") -> 
                "sparkles"
            categoryLower.contains("food") || categoryLower.contains("restaurant") || 
            categoryLower.contains("gourmand") -> 
                "fork.knife"
            categoryLower.contains("loisir") || categoryLower.contains("divertissement") -> 
                "gamecontroller.fill"
            categoryLower.contains("service") || categoryLower.contains("pratique") -> 
                "wrench.and.screwdriver.fill"
            categoryLower.contains("pro") || categoryLower.contains("professionnel") -> 
                "briefcase.fill"
            else -> "person.circle.fill"
        }
    }
}
