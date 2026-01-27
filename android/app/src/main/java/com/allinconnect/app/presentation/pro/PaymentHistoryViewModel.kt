package com.allinconnect.app.presentation.pro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.dto.subscription.PaymentIntentResponse
import com.allinconnect.app.data.repository.SubscriptionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentHistoryViewModel @Inject constructor(
    private val subscriptionsRepository: SubscriptionsRepository
) : ViewModel() {
    
    private val _payments = MutableStateFlow<List<PaymentIntentResponse>>(emptyList())
    val payments: StateFlow<List<PaymentIntentResponse>> = _payments.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    init {
        loadPaymentHistory()
    }
    
    fun loadPaymentHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            subscriptionsRepository.getPaymentHistory().fold(
                onSuccess = { paymentsList ->
                    _payments.value = paymentsList
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
        loadPaymentHistory()
    }
}
