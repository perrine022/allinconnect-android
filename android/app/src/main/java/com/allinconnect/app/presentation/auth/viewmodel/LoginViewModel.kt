package com.allinconnect.app.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.auth.AuthTokenManager
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.core.notifications.PushManager
import com.allinconnect.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authTokenManager: AuthTokenManager,
    private val pushManager: PushManager
) : ViewModel() {
    
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()
    
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess.asStateFlow()
    
    val isValid: Boolean
        get() = _email.value.trim().isNotEmpty() &&
                isValidEmail(_email.value) &&
                _password.value.isNotEmpty() &&
                _password.value.length >= 6
    
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$"
        val pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).matches()
    }
    
    fun updateEmail(email: String) {
        _email.value = email
        _errorMessage.value = null
    }
    
    fun updatePassword(password: String) {
        _password.value = password
        _errorMessage.value = null
    }
    
    fun login() {
        if (!isValid) {
            _errorMessage.value = "Remplis tous les champs correctement"
            return
        }
        
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val result = authRepository.login(
                email = _email.value.trim().lowercase(),
                password = _password.value
            )
            
            result.fold(
                onSuccess = {
                    // Register push token after login
                    launch {
                        pushManager.registerTokenAfterLogin()
                    }
                    _isLoading.value = false
                    _loginSuccess.value = true
                },
                onFailure = { error ->
                    _isLoading.value = false
                    _errorMessage.value = when (error) {
                        is ApiError.HttpError -> {
                            when (error.statusCode) {
                                401, 403 -> "Les informations de connexion sont incorrectes"
                                404 -> "Compte non trouvé"
                                else -> error.message ?: "Un problème s'est produit lors de la connexion"
                            }
                        }
                        is ApiError.NetworkError -> "Problème de connexion. Vérifie ta connexion internet."
                        is ApiError.Unauthorized -> "Les informations de connexion sont incorrectes"
                        else -> "Les informations de connexion sont incorrectes"
                    }
                }
            )
        }
    }
    
    fun isLoggedIn(): Boolean {
        return authTokenManager.getTokenSync() != null
    }
    
    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
