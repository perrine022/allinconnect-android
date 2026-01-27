package com.allinconnect.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.repository.NotificationPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationPreferencesViewModel @Inject constructor(
    private val notificationPreferencesRepository: NotificationPreferencesRepository
) : ViewModel() {
    
    private val _notifyNewOffers = MutableStateFlow(false)
    val notifyNewOffers: StateFlow<Boolean> = _notifyNewOffers.asStateFlow()
    
    private val _notifyNewProNearby = MutableStateFlow(false)
    val notifyNewProNearby: StateFlow<Boolean> = _notifyNewProNearby.asStateFlow()
    
    private val _notifyLocalEvents = MutableStateFlow(false)
    val notifyLocalEvents: StateFlow<Boolean> = _notifyLocalEvents.asStateFlow()
    
    private val _notificationRadius = MutableStateFlow(10)
    val notificationRadius: StateFlow<Int> = _notificationRadius.asStateFlow()
    
    private val _preferredCategories = MutableStateFlow<List<String>>(emptyList())
    val preferredCategories: StateFlow<List<String>> = _preferredCategories.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()
    
    init {
        loadPreferences()
    }
    
    fun loadPreferences() {
        viewModelScope.launch {
            _isLoading.value = true
            notificationPreferencesRepository.getPreferences().fold(
                onSuccess = { preferences ->
                    _notifyNewOffers.value = preferences.notifyNewOffers
                    _notifyNewProNearby.value = preferences.notifyNewProNearby
                    _notifyLocalEvents.value = preferences.notifyLocalEvents
                    _notificationRadius.value = preferences.notificationRadius
                    _preferredCategories.value = preferences.preferredCategories
                    _isLoading.value = false
                },
                onFailure = {
                    _errorMessage.value = "Erreur lors du chargement"
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun updateNotifyNewOffers(enabled: Boolean) {
        _notifyNewOffers.value = enabled
    }
    
    fun updateNotifyNewProNearby(enabled: Boolean) {
        _notifyNewProNearby.value = enabled
    }
    
    fun updateNotifyLocalEvents(enabled: Boolean) {
        _notifyLocalEvents.value = enabled
    }
    
    fun updateNotificationRadius(radius: Int) {
        _notificationRadius.value = radius
    }
    
    fun updatePreferredCategories(categories: List<String>) {
        _preferredCategories.value = categories
    }
    
    fun savePreferences() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val request = com.allinconnect.app.data.dto.notification.NotificationPreferencesRequest(
                notifyNewOffers = _notifyNewOffers.value,
                notifyNewProNearby = _notifyNewProNearby.value,
                notifyLocalEvents = _notifyLocalEvents.value,
                notificationRadius = _notificationRadius.value,
                preferredCategories = _preferredCategories.value
            )
            
            notificationPreferencesRepository.updatePreferences(request).fold(
                onSuccess = {
                    _saveSuccess.value = true
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors de la sauvegarde"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
}
