package com.allinconnect.app.core.config

import com.allinconnect.app.BuildConfig

object ApiConfig {
    const val BASE_URL = BuildConfig.API_BASE_URL
    
    fun getDefaultHeaders(token: String? = null): Map<String, String> {
        val headers = mutableMapOf(
            "Content-Type" to "application/json",
            "Accept" to "application/json"
        )
        
        token?.let {
            headers["Authorization"] = "Bearer $it"
        }
        
        return headers
    }
}
