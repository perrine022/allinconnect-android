package com.allinconnect.app.data.dto.invoice

import kotlinx.serialization.Serializable

@Serializable
data class InvoiceResponse(
    val id: String,
    val amountPaid: Int,
    val status: String,
    val hostedInvoiceUrl: String? = null,
    val invoicePdf: String? = null,
    val created: Int,
    val currency: String,
    val number: String? = null
)

