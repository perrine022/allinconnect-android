package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.profile.*
import retrofit2.http.*

interface ProfileApi {
    @GET("profile/me")
    suspend fun getUserMe(): UserMeResponse
    
    @GET("users/me/light")
    suspend fun getUserLight(): UserLightResponse
    
    @PUT("profile/update")
    suspend fun updateProfile(@Body request: UpdateProfileRequest): UserMeResponse
    
    @POST("profile/change-password")
    suspend fun changePassword(@Body request: ChangePasswordRequest): Unit
    
    @GET("profile/card")
    suspend fun getCard(): CardResponse
}
