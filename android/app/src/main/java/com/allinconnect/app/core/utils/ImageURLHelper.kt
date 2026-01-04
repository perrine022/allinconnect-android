package com.allinconnect.app.core.utils

import com.allinconnect.app.core.config.ApiConfig

object ImageURLHelper {
    /**
     * Construit l'URL complète à partir de establishmentImageUrl
     * Gère les cas :
     * - URL absolue (http:// ou https://) : retourne directement
     * - URL relative commençant par /uploads/ : préfixe avec l'URL de base de l'API
     * - null/empty : retourne null
     */
    fun buildImageURL(establishmentImageUrl: String?): String? {
        if (establishmentImageUrl.isNullOrEmpty()) {
            return null
        }
        
        // Si l'URL commence déjà par http:// ou https://, c'est une URL absolue
        if (establishmentImageUrl.startsWith("http://") || establishmentImageUrl.startsWith("https://")) {
            return establishmentImageUrl
        }
        
        // Si l'URL commence par /uploads/, construire l'URL complète
        if (establishmentImageUrl.startsWith("/uploads/")) {
            val baseURL = ApiConfig.baseUrl.replace("/api/v1", "")
            return "$baseURL$establishmentImageUrl"
        }
        
        // Si l'URL est relative mais ne commence pas par /, ajouter /
        if (!establishmentImageUrl.startsWith("/")) {
            val baseURL = ApiConfig.baseUrl.replace("/api/v1", "")
            return "$baseURL/$establishmentImageUrl"
        }
        
        // Sinon, préfixer avec l'URL de base
        val baseURL = ApiConfig.baseUrl.replace("/api/v1", "")
        return "$baseURL$establishmentImageUrl"
    }
}

