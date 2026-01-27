package com.allinconnect.app.presentation.pro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.dto.invoice.InvoiceResponse
import com.allinconnect.app.data.repository.InvoicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoicesViewModel @Inject constructor(
    private val invoicesRepository: InvoicesRepository
) : ViewModel() {
    
    private val _invoices = MutableStateFlow<List<InvoiceResponse>>(emptyList())
    val invoices: StateFlow<List<InvoiceResponse>> = _invoices.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    init {
        loadInvoices()
    }
    
    fun loadInvoices() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            invoicesRepository.getInvoices().fold(
                onSuccess = { invoicesList ->
                    _invoices.value = invoicesList
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors du chargement"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun refresh() {
        loadInvoices()
    }
}
