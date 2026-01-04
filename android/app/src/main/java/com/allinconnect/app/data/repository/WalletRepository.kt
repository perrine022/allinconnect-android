package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.WalletApi
import com.allinconnect.app.data.dto.wallet.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletRepository @Inject constructor(
    private val walletApi: WalletApi
) {
    suspend fun getWalletHistory(): Result<List<WalletHistoryResponse>> {
        return try {
            val response = walletApi.getWalletHistory()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun createWalletRequest(amount: Double, professionals: String): Result<WalletRequestResponse> {
        return try {
            val request = WalletRequest(amount = amount, professionals = professionals)
            val response = walletApi.createWalletRequest(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
    
    suspend fun getWalletRequests(): Result<List<WalletRequestResponse>> {
        return try {
            val response = walletApi.getWalletRequests()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

