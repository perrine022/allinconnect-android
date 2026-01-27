package com.allinconnect.app.presentation.profile

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
class ChangePasswordViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    
    private val _oldPassword = MutableStateFlow("")
    val oldPassword: StateFlow<String> = _oldPassword.asStateFlow()
    
    private val _newPassword = MutableStateFlow("")
    val newPassword: StateFlow<String> = _newPassword.asStateFlow()
    
    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success.asStateFlow()
    
    val isValid: Boolean
        get() = _oldPassword.value.isNotEmpty() &&
                _newPassword.value.length >= 6 &&
                _newPassword.value == _confirmPassword.value
    
    fun updateOldPassword(password: String) {
        _oldPassword.value = password
        _errorMessage.value = null
    }
    
    fun updateNewPassword(password: String) {
        _newPassword.value = password
        _errorMessage.value = null
    }
    
    fun updateConfirmPassword(password: String) {
        _confirmPassword.value = password
        _errorMessage.value = null
    }
    
    fun changePassword() {
        if (!isValid) {
            _errorMessage.value = "Vérifie que tous les champs sont remplis et que les mots de passe correspondent"
            return
        }
        
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            profileRepository.changePassword(
                oldPassword = _oldPassword.value,
                newPassword = _newPassword.value
            ).fold(
                onSuccess = {
                    _success.value = true
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.HttpError -> {
                            when (error.statusCode) {
                                401 -> "Ancien mot de passe incorrect"
                                else -> error.message ?: "Erreur lors du changement de mot de passe"
                            }
                        }
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors du changement de mot de passe"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
}
