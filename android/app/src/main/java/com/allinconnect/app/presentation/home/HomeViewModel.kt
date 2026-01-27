package com.allinconnect.app.presentation.home

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
class HomeViewModel @Inject constructor(
    private val offersRepository: OffersRepository,
    private val partnersRepository: PartnersRepository
) : ViewModel() {
    
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers.asStateFlow()
    
    private val _partners = MutableStateFlow<List<Partner>>(emptyList())
    val partners: StateFlow<List<Partner>> = _partners.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadData()
    }
    
    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            
            offersRepository.getOffers().fold(
                onSuccess = { _offers.value = it },
                onFailure = { }
            )
            
            partnersRepository.getAllProfessionals().fold(
                onSuccess = { _partners.value = it },
                onFailure = { }
            )
            
            _isLoading.value = false
        }
    }
}
