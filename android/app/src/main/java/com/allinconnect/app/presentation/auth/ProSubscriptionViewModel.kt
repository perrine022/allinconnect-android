package com.allinconnect.app.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.dto.subscription.SubscriptionPlanResponse
import com.allinconnect.app.data.dto.billing.SubscriptionPaymentSheetResponse
import com.allinconnect.app.data.repository.SubscriptionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProSubscriptionViewModel @Inject constructor(
    private val subscriptionsRepository: SubscriptionsRepository
) : ViewModel() {
    
    private val _plans = MutableStateFlow<List<SubscriptionPlanResponse>>(emptyList())
    val plans: StateFlow<List<SubscriptionPlanResponse>> = _plans.asStateFlow()
    
    private val _selectedPlan = MutableStateFlow<SubscriptionPlanResponse?>(null)
    val selectedPlan: StateFlow<SubscriptionPlanResponse?> = _selectedPlan.asStateFlow()
    
    private val _paymentSheetData = MutableStateFlow<SubscriptionPaymentSheetResponse?>(null)
    val paymentSheetData: StateFlow<SubscriptionPaymentSheetResponse?> = _paymentSheetData.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    init {
        loadPlans()
    }
    
    fun loadPlans() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            subscriptionsRepository.getPlans().fold(
                onSuccess = { plansList ->
                    // Filter pro plans (category = "PRO")
                    _plans.value = plansList.filter { it.category == "PRO" }
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
    
    fun selectPlan(plan: SubscriptionPlanResponse) {
        _selectedPlan.value = plan
    }
    
    fun subscribeToPlan(planId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val request = mapOf("planId" to planId)
            subscriptionsRepository.subscribe(request).fold(
                onSuccess = { paymentSheetData ->
                    _paymentSheetData.value = paymentSheetData
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors de l'abonnement"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
}
