package com.allinconnect.app.data.repository

import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.api.InvoicesApi
import com.allinconnect.app.data.dto.invoice.InvoiceResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoicesRepository @Inject constructor(
    private val invoicesApi: InvoicesApi
) {
    suspend fun getInvoices(): Result<List<InvoiceResponse>> {
        return try {
            val response = invoicesApi.getInvoices()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(ApiError.NetworkError(e))
        }
    }
}

