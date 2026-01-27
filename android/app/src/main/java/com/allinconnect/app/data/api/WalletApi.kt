package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.wallet.*
import retrofit2.http.*

interface WalletApi {
    @GET("wallet/history")
    suspend fun getWalletHistory(): List<WalletHistoryResponse>
    
    @POST("wallet/request")
    suspend fun createWalletRequest(@Body request: WalletRequest): WalletRequestResponse
}
