package com.allinconnect.app.presentation.billing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.repository.BillingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageSubscriptionViewModel @Inject constructor(
    private val billingRepository: BillingRepository
) : ViewModel() {
    
    private val _subscriptionStatus = MutableStateFlow<com.allinconnect.app.data.dto.billing.SubscriptionStatusResponse?>(null)
    val subscriptionStatus: StateFlow<com.allinconnect.app.data.dto.billing.SubscriptionStatusResponse?> = _subscriptionStatus.asStateFlow()
    
    private val _portalUrl = MutableStateFlow<String?>(null)
    val portalUrl: StateFlow<String?> = _portalUrl.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _cancelSuccess = MutableStateFlow(false)
    val cancelSuccess: StateFlow<Boolean> = _cancelSuccess.asStateFlow()
    
    init {
        loadSubscriptionStatus()
    }
    
    fun loadSubscriptionStatus() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            billingRepository.getSubscriptionStatus().fold(
                onSuccess = { status ->
                    _subscriptionStatus.value = status
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
    
    fun openPortal() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            billingRepository.createPortalSession().fold(
                onSuccess = { portal ->
                    _portalUrl.value = portal.url
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors de l'ouverture du portail"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun cancelSubscription() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            billingRepository.cancelSubscription().fold(
                onSuccess = {
                    _cancelSuccess.value = true
                    _isLoading.value = false
                    loadSubscriptionStatus()
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors de l'annulation"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
}
