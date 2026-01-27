package com.allinconnect.app.presentation.pro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.repository.OffersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateOfferViewModel @Inject constructor(
    private val offersRepository: OffersRepository
) : ViewModel() {
    
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()
    
    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description.asStateFlow()
    
    private val _discount = MutableStateFlow("")
    val discount: StateFlow<String> = _discount.asStateFlow()
    
    private val _startDate = MutableStateFlow("")
    val startDate: StateFlow<String> = _startDate.asStateFlow()
    
    private val _endDate = MutableStateFlow("")
    val endDate: StateFlow<String> = _endDate.asStateFlow()
    
    private val _category = MutableStateFlow<String?>(null)
    val category: StateFlow<String?> = _category.asStateFlow()
    
    private val _type = MutableStateFlow<String?>(null)
    val type: StateFlow<String?> = _type.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _createSuccess = MutableStateFlow(false)
    val createSuccess: StateFlow<Boolean> = _createSuccess.asStateFlow()
    
    val isValid: Boolean
        get() = _title.value.isNotBlank() &&
                _description.value.isNotBlank() &&
                _discount.value.isNotBlank() &&
                _endDate.value.isNotBlank()
    
    fun updateTitle(title: String) {
        _title.value = title
        _errorMessage.value = null
    }
    
    fun updateDescription(description: String) {
        _description.value = description
        _errorMessage.value = null
    }
    
    fun updateDiscount(discount: String) {
        _discount.value = discount
        _errorMessage.value = null
    }
    
    fun updateStartDate(startDate: String) {
        _startDate.value = startDate
        _errorMessage.value = null
    }
    
    fun updateEndDate(endDate: String) {
        _endDate.value = endDate
        _errorMessage.value = null
    }
    
    fun updateCategory(category: String?) {
        _category.value = category
        _errorMessage.value = null
    }
    
    fun updateType(type: String?) {
        _type.value = type
        _errorMessage.value = null
    }
    
    fun createOffer() {
        if (!isValid) {
            _errorMessage.value = "Remplis tous les champs obligatoires"
            return
        }
        
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val offerData = mutableMapOf<String, Any>(
                "title" to _title.value,
                "description" to _description.value,
                "discount" to _discount.value,
                "endDate" to _endDate.value
            )
            
            if (_startDate.value.isNotBlank()) {
                offerData["startDate"] = _startDate.value
            }
            
            _category.value?.let { offerData["category"] = it }
            _type.value?.let { offerData["type"] = it }
            
            offersRepository.createOffer(offerData).fold(
                onSuccess = {
                    _createSuccess.value = true
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors de la création"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
}
