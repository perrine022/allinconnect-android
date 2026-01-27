package com.allinconnect.app.presentation.pro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.dto.profile.UpdateProfileRequest
import com.allinconnect.app.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageEstablishmentViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    
    private val _establishmentName = MutableStateFlow("")
    val establishmentName: StateFlow<String> = _establishmentName.asStateFlow()
    
    private val _establishmentDescription = MutableStateFlow("")
    val establishmentDescription: StateFlow<String> = _establishmentDescription.asStateFlow()
    
    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()
    
    private val _website = MutableStateFlow("")
    val website: StateFlow<String> = _website.asStateFlow()
    
    private val _instagram = MutableStateFlow("")
    val instagram: StateFlow<String> = _instagram.asStateFlow()
    
    private val _openingHours = MutableStateFlow("")
    val openingHours: StateFlow<String> = _openingHours.asStateFlow()
    
    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address.asStateFlow()
    
    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _updateSuccess = MutableStateFlow(false)
    val updateSuccess: StateFlow<Boolean> = _updateSuccess.asStateFlow()
    
    init {
        loadEstablishmentData()
    }
    
    fun loadEstablishmentData() {
        viewModelScope.launch {
            _isLoading.value = true
            profileRepository.getUserMe().fold(
                onSuccess = { userMe ->
                    _establishmentName.value = userMe.establishmentName ?: ""
                    _establishmentDescription.value = userMe.establishmentDescription ?: ""
                    _phoneNumber.value = userMe.phoneNumber ?: ""
                    _website.value = userMe.website ?: ""
                    _instagram.value = userMe.instagram ?: ""
                    _openingHours.value = userMe.openingHours ?: ""
                    _address.value = userMe.address ?: ""
                    _city.value = userMe.city ?: ""
                    _isLoading.value = false
                },
                onFailure = {
                    _errorMessage.value = "Erreur lors du chargement"
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun updateEstablishmentName(name: String) {
        _establishmentName.value = name
    }
    
    fun updateEstablishmentDescription(description: String) {
        _establishmentDescription.value = description
    }
    
    fun updatePhoneNumber(phone: String) {
        _phoneNumber.value = phone
    }
    
    fun updateWebsite(website: String) {
        _website.value = website
    }
    
    fun updateInstagram(instagram: String) {
        _instagram.value = instagram
    }
    
    fun updateOpeningHours(hours: String) {
        _openingHours.value = hours
    }
    
    fun updateAddress(address: String) {
        _address.value = address
    }
    
    fun updateCity(city: String) {
        _city.value = city
    }
    
    fun saveEstablishment() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val request = UpdateProfileRequest(
                establishmentName = _establishmentName.value.takeIf { it.isNotEmpty() },
                establishmentDescription = _establishmentDescription.value.takeIf { it.isNotEmpty() },
                phoneNumber = _phoneNumber.value.takeIf { it.isNotEmpty() },
                website = _website.value.takeIf { it.isNotEmpty() },
                instagram = _instagram.value.takeIf { it.isNotEmpty() },
                openingHours = _openingHours.value.takeIf { it.isNotEmpty() },
                address = _address.value.takeIf { it.isNotEmpty() },
                city = _city.value.takeIf { it.isNotEmpty() }
            )
            
            profileRepository.updateProfile(request).fold(
                onSuccess = {
                    _updateSuccess.value = true
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
