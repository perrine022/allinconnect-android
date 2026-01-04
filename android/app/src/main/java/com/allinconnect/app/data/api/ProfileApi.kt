package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.profile.*
import retrofit2.http.*

interface ProfileApi {
    @PUT("/users/profile")
    suspend fun updateProfile(@Body request: UpdateProfileRequest)
    
    @POST("/users/change-password")
    suspend fun changePassword(@Body request: ChangePasswordRequest)
    
    @GET("/users/me/light")
    suspend fun getUserLight(): UserLightResponse
    
    @GET("/users/me")
    suspend fun getUserMe(): UserMeResponse
}

