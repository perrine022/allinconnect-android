package com.allinconnect.app.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
        updateValidation()
    }
    
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        updateValidation()
    }
    
    private fun updateValidation() {
        val state = _uiState.value
        val isValid = state.email.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches() &&
                state.password.length >= 6
        _uiState.value = state.copy(isValid = isValid)
    }
    
    fun login() {
        val state = _uiState.value
        if (!state.isValid) {
            _uiState.value = state.copy(
                errorMessage = "Veuillez remplir tous les champs correctement"
            )
            return
        }
        
        _uiState.value = state.copy(isLoading = true, errorMessage = null)
        
        viewModelScope.launch {
            authRepository.login(state.email, state.password)
                .onSuccess {
                    _uiState.value = state.copy(isLoading = false, isLoggedIn = true)
                }
                .onFailure { error ->
                    _uiState.value = state.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Erreur lors de la connexion"
                    )
                }
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isValid: Boolean = false,
    val isLoggedIn: Boolean = false
)

