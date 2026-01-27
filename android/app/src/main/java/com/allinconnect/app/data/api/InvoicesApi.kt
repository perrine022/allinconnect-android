package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.invoice.InvoiceResponse
import retrofit2.http.*

interface InvoicesApi {
    @GET("invoices")
    suspend fun getInvoices(): List<InvoiceResponse>
}
