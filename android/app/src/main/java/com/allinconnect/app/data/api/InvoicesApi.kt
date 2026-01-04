package com.allinconnect.app.data.api

import com.allinconnect.app.data.dto.invoice.InvoiceResponse
import retrofit2.http.GET

interface InvoicesApi {
    @GET("/billing/invoices")
    suspend fun getInvoices(): List<InvoiceResponse>
}

