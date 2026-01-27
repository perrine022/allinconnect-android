package com.allinconnect.app.presentation.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.data.dto.payment.PaymentSheetResponse
import com.allinconnect.app.data.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StripePaymentSheetViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository
) : ViewModel() {
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _paymentSheetData = MutableStateFlow<PaymentSheetResponse?>(null)
    val paymentSheetData: StateFlow<PaymentSheetResponse?> = _paymentSheetData.asStateFlow()
    
    fun loadPaymentSheetData(clientSecret: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            // The clientSecret is the paymentIntentClientSecret from the API
            // We need to extract payment sheet data from it or fetch it from API
            // For now, we'll assume the clientSecret contains all needed info
            // In production, you'd fetch PaymentSheetResponse from API using PaymentRepository
            _isLoading.value = false
        }
    }
    
    fun setError(message: String) {
        _errorMessage.value = message
        _isLoading.value = false
    }
}
