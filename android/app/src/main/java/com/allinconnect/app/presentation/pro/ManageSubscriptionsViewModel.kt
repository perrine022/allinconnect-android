package com.allinconnect.app.presentation.pro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.repository.SubscriptionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageSubscriptionsViewModel @Inject constructor(
    private val subscriptionsRepository: SubscriptionsRepository
) : ViewModel() {
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    // TODO: Add subscription list when API is available
    fun loadSubscriptions() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            // TODO: Implement when API endpoint is available
            _isLoading.value = false
        }
    }
}
