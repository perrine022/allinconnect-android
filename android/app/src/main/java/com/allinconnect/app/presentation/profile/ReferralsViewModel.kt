package com.allinconnect.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReferralsViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    
    private val _referralCode = MutableStateFlow<String?>(null)
    val referralCode: StateFlow<String?> = _referralCode.asStateFlow()
    
    private val _referralCount = MutableStateFlow(0)
    val referralCount: StateFlow<Int> = _referralCount.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    val referralQRCodeURL: String
        get() = _referralCode.value?.let { "https://allinconnect-form.vercel.app/?code=$it" } ?: ""
    
    init {
        loadReferralData()
    }
    
    fun loadReferralData() {
        viewModelScope.launch {
            _isLoading.value = true
            profileRepository.getUserLight().fold(
                onSuccess = { userLight ->
                    _referralCode.value = userLight.referralCode
                    _referralCount.value = userLight.referralCount ?: 0
                    _isLoading.value = false
                },
                onFailure = {
                    _isLoading.value = false
                }
            )
        }
    }
}
