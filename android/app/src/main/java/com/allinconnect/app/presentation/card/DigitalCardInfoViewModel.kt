package com.allinconnect.app.presentation.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DigitalCardInfoViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    
    private val _cardNumber = MutableStateFlow<String?>(null)
    val cardNumber: StateFlow<String?> = _cardNumber.asStateFlow()
    
    private val _cardType = MutableStateFlow<String?>(null)
    val cardType: StateFlow<String?> = _cardType.asStateFlow()
    
    private val _isCardActive = MutableStateFlow(false)
    val isCardActive: StateFlow<Boolean> = _isCardActive.asStateFlow()
    
    private val _members = MutableStateFlow<List<com.allinconnect.app.data.dto.profile.CardMember>>(emptyList())
    val members: StateFlow<List<com.allinconnect.app.data.dto.profile.CardMember>> = _members.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    init {
        loadCardInfo()
    }
    
    fun loadCardInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            profileRepository.getCard().fold(
                onSuccess = { card ->
                    _cardNumber.value = card.cardNumber
                    _cardType.value = card.type
                    _isCardActive.value = true
                    _members.value = card.members ?: emptyList()
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors du chargement"
                    }
                    _isCardActive.value = false
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun refresh() {
        loadCardInfo()
    }
}
