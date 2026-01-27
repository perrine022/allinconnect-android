package com.allinconnect.app.presentation.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.dto.savings.SavingsResponse
import com.allinconnect.app.data.repository.SavingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavingsListViewModel @Inject constructor(
    private val savingsRepository: SavingsRepository
) : ViewModel() {
    
    private val _savings = MutableStateFlow<List<SavingsResponse>>(emptyList())
    val savings: StateFlow<List<SavingsResponse>> = _savings.asStateFlow()
    
    private val _totalSavings = MutableStateFlow(0.0)
    val totalSavings: StateFlow<Double> = _totalSavings.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    init {
        loadSavings()
    }
    
    fun loadSavings() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            savingsRepository.getSavings().fold(
                onSuccess = { savingsList ->
                    _savings.value = savingsList
                    _totalSavings.value = savingsList.sumOf { it.amount }
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
    
    fun refreshSavings() {
        loadSavings()
    }
}
