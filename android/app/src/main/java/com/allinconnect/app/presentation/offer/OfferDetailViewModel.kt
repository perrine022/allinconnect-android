package com.allinconnect.app.presentation.offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.data.repository.OffersRepository
import com.allinconnect.app.data.repository.PartnersRepository
import com.allinconnect.app.domain.model.Offer
import com.allinconnect.app.domain.model.Partner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferDetailViewModel @Inject constructor(
    private val offersRepository: OffersRepository,
    private val partnersRepository: PartnersRepository
) : ViewModel() {
    
    private val _offer = MutableStateFlow<Offer?>(null)
    val offer: StateFlow<Offer?> = _offer.asStateFlow()
    
    private val _partner = MutableStateFlow<Partner?>(null)
    val partner: StateFlow<Partner?> = _partner.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    fun loadOfferDetail(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            offersRepository.getOfferById(id).fold(
                onSuccess = { offer ->
                    _offer.value = offer
                    offer.partnerId?.toIntOrNull()?.let { partnerId ->
                        partnersRepository.getProfessionalById(partnerId).fold(
                            onSuccess = { _partner.value = it },
                            onFailure = { }
                        )
                    }
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = "Erreur lors du chargement"
                    _isLoading.value = false
                }
            )
        }
    }
}
