package com.allinconnect.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.dto.profile.UpdateProfileRequest
import com.allinconnect.app.data.repository.ProfileRepository
import com.allinconnect.app.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()
    
    private val _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName.asStateFlow()
    
    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName.asStateFlow()
    
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()
    
    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address.asStateFlow()
    
    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city.asStateFlow()
    
    private val _postalCode = MutableStateFlow("")
    val postalCode: StateFlow<String> = _postalCode.asStateFlow()
    
    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _updateSuccess = MutableStateFlow(false)
    val updateSuccess: StateFlow<Boolean> = _updateSuccess.asStateFlow()
    
    init {
        loadUserData()
    }
    
    fun loadUserData() {
        viewModelScope.launch {
            _isLoading.value = true
            profileRepository.getUserMe().fold(
                onSuccess = { userMe ->
                    _user.value = User(
                        id = userMe.id.toString(),
                        firstName = userMe.firstName ?: "",
                        lastName = userMe.lastName ?: "",
                        username = userMe.email,
                        bio = "",
                        profileImageName = "person.circle.fill",
                        userType = when (userMe.userType) {
                            "PRO", "PROFESSIONAL" -> com.allinconnect.app.domain.model.UserType.PRO
                            else -> com.allinconnect.app.domain.model.UserType.CLIENT
                        }
                    )
                    _firstName.value = userMe.firstName ?: ""
                    _lastName.value = userMe.lastName ?: ""
                    _email.value = userMe.email
                    _address.value = userMe.address ?: ""
                    _city.value = userMe.city ?: ""
                    _postalCode.value = userMe.postalCode ?: ""
                    _phoneNumber.value = userMe.phoneNumber ?: ""
                    _isLoading.value = false
                },
                onFailure = {
                    _errorMessage.value = "Erreur lors du chargement"
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun updateFirstName(firstName: String) {
        _firstName.value = firstName
    }
    
    fun updateLastName(lastName: String) {
        _lastName.value = lastName
    }
    
    fun updateEmail(email: String) {
        _email.value = email
    }
    
    fun updateAddress(address: String) {
        _address.value = address
    }
    
    fun updateCity(city: String) {
        _city.value = city
    }
    
    fun updatePostalCode(postalCode: String) {
        _postalCode.value = postalCode
    }
    
    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }
    
    fun updateProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val request = UpdateProfileRequest(
                firstName = _firstName.value.takeIf { it.isNotEmpty() },
                lastName = _lastName.value.takeIf { it.isNotEmpty() },
                email = _email.value.takeIf { it.isNotEmpty() },
                address = _address.value.takeIf { it.isNotEmpty() },
                city = _city.value.takeIf { it.isNotEmpty() },
                postalCode = _postalCode.value.takeIf { it.isNotEmpty() },
                phoneNumber = _phoneNumber.value.takeIf { it.isNotEmpty() }
            )
            
            profileRepository.updateProfile(request).fold(
                onSuccess = {
                    _updateSuccess.value = true
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.HttpError -> error.message ?: "Erreur lors de la mise à jour"
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors de la mise à jour"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
}
