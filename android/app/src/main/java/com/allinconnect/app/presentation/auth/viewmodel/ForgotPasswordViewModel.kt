package com.allinconnect.app.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _isEmailSent = MutableStateFlow(false)
    val isEmailSent: StateFlow<Boolean> = _isEmailSent.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    val isValid: Boolean
        get() = _email.value.trim().isNotEmpty() && isValidEmail(_email.value)
    
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$"
        val pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).matches()
    }
    
    fun updateEmail(email: String) {
        _email.value = email
        _errorMessage.value = null
    }
    
    fun sendResetEmail() {
        if (!isValid) {
            _errorMessage.value = "Veuillez entrer une adresse email valide"
            return
        }
        
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _isEmailSent.value = true
            
            authRepository.forgotPassword(_email.value.trim().lowercase()).fold(
                onSuccess = {
                    _isLoading.value = false
                },
                onFailure = {
                    _isLoading.value = false
                    // Keep isEmailSent = true to not disturb user
                }
            )
        }
    }
}
