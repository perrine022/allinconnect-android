package com.allinconnect.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.data.repository.ProfileRepository
import com.allinconnect.app.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
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
                    _isLoading.value = false
                },
                onFailure = {
                    _isLoading.value = false
                }
            )
        }
    }
}
