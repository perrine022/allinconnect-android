package com.allinconnect.app.core.utils

import com.allinconnect.app.core.config.ApiConfig

object ImageURLHelper {
    fun buildImageURL(establishmentImageUrl: String?): String? {
        if (establishmentImageUrl.isNullOrEmpty()) {
            return null
        }
        
        // If URL already starts with http:// or https://, it's absolute
        if (establishmentImageUrl.startsWith("http://") || establishmentImageUrl.startsWith("https://")) {
            return establishmentImageUrl
        }
        
        // If URL starts with /uploads/, build full URL
        val baseURL = ApiConfig.BASE_URL.replace("/api/v1", "")
        
        return if (establishmentImageUrl.startsWith("/")) {
            "$baseURL$establishmentImageUrl"
        } else {
            "$baseURL/$establishmentImageUrl"
        }
    }
}
