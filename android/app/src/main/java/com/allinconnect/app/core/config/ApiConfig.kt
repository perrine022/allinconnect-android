package com.allinconnect.app.core.config

import com.allinconnect.app.BuildConfig

object ApiConfig {
    val baseUrl: String = BuildConfig.API_BASE_URL
    
    val defaultHeaders: Map<String, String>
        get() = mapOf(
            "Content-Type" to "application/json",
            "Accept" to "application/json"
        )
}

