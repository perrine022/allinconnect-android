package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.push.RegisterTokenRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface PushApi {
    @POST("/push/register")
    suspend fun registerToken(@Body request: RegisterTokenRequest)
}

