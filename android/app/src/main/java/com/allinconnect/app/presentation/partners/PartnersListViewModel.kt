package com.allinconnect.app.presentation.partners

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.data.repository.PartnersRepository
import com.allinconnect.app.domain.model.Partner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnersListViewModel @Inject constructor(
    private val partnersRepository: PartnersRepository
) : ViewModel() {
    
    private val _partners = MutableStateFlow<List<Partner>>(emptyList())
    val partners: StateFlow<List<Partner>> = _partners.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadPartners()
    }
    
    fun loadPartners() {
        viewModelScope.launch {
            _isLoading.value = true
            partnersRepository.getAllProfessionals().fold(
                onSuccess = { _partners.value = it },
                onFailure = { }
            )
            _isLoading.value = false
        }
    }
}
