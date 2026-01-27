package com.allinconnect.app.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.auth.AuthTokenManager
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.core.notifications.PushManager
import com.allinconnect.app.data.dto.auth.RegistrationRequest
import com.allinconnect.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authTokenManager: AuthTokenManager,
    private val pushManager: PushManager
) : ViewModel() {
    
    private val _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName.asStateFlow()
    
    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName.asStateFlow()
    
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()
    
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()
    
    private val _postalCode = MutableStateFlow("")
    val postalCode: StateFlow<String> = _postalCode.asStateFlow()
    
    private val _birthDay = MutableStateFlow("")
    val birthDay: StateFlow<String> = _birthDay.asStateFlow()
    
    private val _birthMonth = MutableStateFlow("")
    val birthMonth: StateFlow<String> = _birthMonth.asStateFlow()
    
    private val _birthYear = MutableStateFlow("")
    val birthYear: StateFlow<String> = _birthYear.asStateFlow()
    
    private val _referralCode = MutableStateFlow("")
    val referralCode: StateFlow<String> = _referralCode.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _signUpSuccess = MutableStateFlow(false)
    val signUpSuccess: StateFlow<Boolean> = _signUpSuccess.asStateFlow()
    
    val isValid: Boolean
        get() = _firstName.value.trim().isNotEmpty() &&
                _lastName.value.trim().isNotEmpty() &&
                _email.value.trim().isNotEmpty() &&
                isValidEmail(_email.value) &&
                _password.value.isNotEmpty() &&
                _password.value.length >= 6 &&
                _postalCode.value.trim().isNotEmpty() &&
                _postalCode.value.length == 5 &&
                isValidBirthDate()
    
    val isValidEmail: Boolean
        get() = isValidEmail(_email.value)
    
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$"
        val pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).matches()
    }
    
    private fun isValidBirthDate(): Boolean {
        val day = _birthDay.value.toIntOrNull() ?: return false
        val month = _birthMonth.value.toIntOrNull() ?: return false
        val year = _birthYear.value.toIntOrNull() ?: return false
        
        if (day < 1 || day > 31 || month < 1 || month > 12) return false
        
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        if (year < 1900 || year > currentYear) return false
        
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        
        return calendar.get(Calendar.DAY_OF_MONTH) == day &&
               calendar.get(Calendar.MONTH) == month - 1 &&
               calendar.get(Calendar.YEAR) == year
    }
    
    private fun formatBirthDate(): String? {
        val day = _birthDay.value.toIntOrNull() ?: return null
        val month = _birthMonth.value.toIntOrNull() ?: return null
        val year = _birthYear.value.toIntOrNull() ?: return null
        
        return String.format("%04d-%02d-%02d", year, month, day)
    }
    
    fun updateFirstName(firstName: String) {
        _firstName.value = firstName
        _errorMessage.value = null
    }
    
    fun updateLastName(lastName: String) {
        _lastName.value = lastName
        _errorMessage.value = null
    }
    
    fun updateEmail(email: String) {
        _email.value = email
        _errorMessage.value = null
    }
    
    fun updatePassword(password: String) {
        _password.value = password
        _errorMessage.value = null
    }
    
    fun updatePostalCode(postalCode: String) {
        _postalCode.value = postalCode.filter { it.isDigit() }.take(5)
        _errorMessage.value = null
    }
    
    fun updateBirthDay(day: String) {
        _birthDay.value = day.filter { it.isDigit() }.take(2)
        _errorMessage.value = null
    }
    
    fun updateBirthMonth(month: String) {
        _birthMonth.value = month.filter { it.isDigit() }.take(2)
        _errorMessage.value = null
    }
    
    fun updateBirthYear(year: String) {
        _birthYear.value = year.filter { it.isDigit() }.take(4)
        _errorMessage.value = null
    }
    
    fun updateReferralCode(code: String) {
        _referralCode.value = code
        _errorMessage.value = null
    }
    
    fun signUp(onSuccess: (Boolean) -> Unit) {
        if (!isValid) {
            _errorMessage.value = "Remplis tous les champs correctement"
            onSuccess(false)
            return
        }
        
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val birthDateString = formatBirthDate()
            if (birthDateString == null) {
                _errorMessage.value = "Date de naissance invalide"
                _isLoading.value = false
                onSuccess(false)
                return@launch
            }
            
            val request = RegistrationRequest(
                firstName = _firstName.value.trim(),
                lastName = _lastName.value.trim(),
                email = _email.value.trim().lowercase(),
                password = _password.value,
                address = null,
                city = _postalCode.value,
                postalCode = _postalCode.value.takeIf { it.isNotEmpty() },
                birthDate = birthDateString,
                userType = null,
                subscriptionType = "FREE",
                profession = null,
                category = null,
                subCategory = null,
                referralCode = _referralCode.value.takeIf { it.isNotEmpty() }
            )
            
            val result = authRepository.register(request)
            
            result.fold(
                onSuccess = {
                    // Register push token after signup
                    launch {
                        pushManager.registerTokenAfterLogin()
                    }
                    _isLoading.value = false
                    _signUpSuccess.value = true
                    onSuccess(true)
                },
                onFailure = { error ->
                    _isLoading.value = false
                    _errorMessage.value = when (error) {
                        is ApiError.HttpError -> {
                            when (error.statusCode) {
                                400 -> error.message ?: "Les informations fournies sont invalides"
                                409 -> "Cet email est déjà utilisé"
                                else -> error.message ?: "Erreur lors de l'inscription"
                            }
                        }
                        is ApiError.NetworkError -> "Erreur de connexion. Vérifie ta connexion internet."
                        else -> "Erreur lors de l'inscription"
                    }
                    onSuccess(false)
                }
            )
        }
    }
}
