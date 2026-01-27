package com.allinconnect.app.presentation.pro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.repository.OffersRepository
import com.allinconnect.app.data.repository.ProfileRepository
import com.allinconnect.app.domain.model.Offer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProOffersViewModel @Inject constructor(
    private val offersRepository: OffersRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {
    
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _deleteSuccess = MutableStateFlow(false)
    val deleteSuccess: StateFlow<Boolean> = _deleteSuccess.asStateFlow()
    
    init {
        loadProOffers()
    }
    
    fun loadProOffers() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            // Load user to check if Pro
            profileRepository.getUserMe().fold(
                onSuccess = { userMe ->
                    if (userMe.userType == "PRO" || userMe.userType == "PROFESSIONAL") {
                        // Load offers for this Pro user
                        offersRepository.getOffers().fold(
                            onSuccess = { offersList ->
                                _offers.value = offersList
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
                    } else {
                        _errorMessage.value = "Accès réservé aux professionnels"
                        _isLoading.value = false
                    }
                },
                onFailure = {
                    _errorMessage.value = "Erreur lors du chargement"
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun deleteOffer(offerId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            offersRepository.deleteOffer(offerId).fold(
                onSuccess = {
                    _deleteSuccess.value = true
                    _isLoading.value = false
                    loadProOffers()
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors de la suppression"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun archiveOffer(offerId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            offersRepository.archiveOffer(offerId).fold(
                onSuccess = {
                    _isLoading.value = false
                    loadProOffers()
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors de l'archivage"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun refresh() {
        loadProOffers()
    }
}
