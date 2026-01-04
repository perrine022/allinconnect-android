package com.allinconnect.app.core.utils

import com.allinconnect.app.data.dto.offer.OfferCategory

object DefaultImageHelper {
    /**
     * Retourne une icône par défaut pour une catégorie d'offre
     * Note: En Android, on utilise des drawable resources ou des icônes Material
     */
    fun defaultImageForOfferCategory(category: OfferCategory?): String {
        if (category == null) {
            return "tag" // Default icon name
        }
        
        return when (category) {
            OfferCategory.SANTE_BIEN_ETRE -> "fitness_center" // Sport/Santé
            OfferCategory.BEAUTE_ESTHETIQUE -> "spa" // Beauté
            OfferCategory.FOOD_PLAISIRS -> "restaurant" // Food
            OfferCategory.LOISIRS_DIVERTISSEMENTS -> "sports_esports" // Loisirs
            OfferCategory.SERVICE_PRATIQUES -> "build" // Services
            OfferCategory.ENTRE_PROS -> "business" // Entre pros
        }
    }
    
    /**
     * Retourne une icône par défaut pour une catégorie de partenaire (string)
     */
    fun defaultImageForPartnerCategory(category: String): String {
        val categoryLower = category.lowercase()
        
        return when {
            categoryLower.contains("sport") || categoryLower.contains("santé") || 
            categoryLower.contains("sante") || categoryLower.contains("bien être") || 
            categoryLower.contains("bien-etre") || categoryLower.contains("fitness") || 
            categoryLower.contains("gym") -> "fitness_center"
            
            categoryLower.contains("beauté") || categoryLower.contains("beaute") || 
            categoryLower.contains("esthétique") || categoryLower.contains("esthetique") || 
            categoryLower.contains("spa") || categoryLower.contains("coiffure") -> "spa"
            
            categoryLower.contains("food") || categoryLower.contains("restaurant") || 
            categoryLower.contains("gourmand") || categoryLower.contains("boulangerie") || 
            categoryLower.contains("café") || categoryLower.contains("cafe") -> "restaurant"
            
            categoryLower.contains("loisir") || categoryLower.contains("divertissement") || 
            categoryLower.contains("jeu") || categoryLower.contains("cinéma") || 
            categoryLower.contains("cinema") || categoryLower.contains("théâtre") || 
            categoryLower.contains("theatre") -> "sports_esports"
            
            categoryLower.contains("service") || categoryLower.contains("pratique") || 
            categoryLower.contains("réparation") || categoryLower.contains("reparation") || 
            categoryLower.contains("plomberie") || categoryLower.contains("électricité") || 
            categoryLower.contains("electricite") -> "build"
            
            categoryLower.contains("pro") || categoryLower.contains("professionnel") || 
            categoryLower.contains("entreprise") || categoryLower.contains("business") -> "business"
            
            else -> "person" // Par défaut
        }
    }
    
    /**
     * Retourne une icône par défaut pour une catégorie d'offre (string)
     */
    fun defaultImageForOfferCategoryString(category: String?): String {
        if (category == null) {
            return "tag"
        }
        
        // Essayer de mapper vers OfferCategory
        val offerCategory = OfferCategory.values().firstOrNull { 
            it.name.equals(category, ignoreCase = true) 
        }
        
        if (offerCategory != null) {
            return defaultImageForOfferCategory(offerCategory)
        }
        
        // Sinon utiliser le mapping par mots-clés
        return defaultImageForPartnerCategory(category)
    }
}

