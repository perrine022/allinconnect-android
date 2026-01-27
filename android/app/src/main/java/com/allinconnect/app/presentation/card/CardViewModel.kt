package com.allinconnect.app.presentation.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.data.repository.ProfileRepository
import com.allinconnect.app.data.repository.SavingsRepository
import com.allinconnect.app.data.repository.SubscriptionsRepository
import com.allinconnect.app.data.repository.FavoritesRepository
import com.allinconnect.app.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val savingsRepository: SavingsRepository,
    private val subscriptionsRepository: SubscriptionsRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {
    
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()
    
    private val _savings = MutableStateFlow(0.0)
    val savings: StateFlow<Double> = _savings.asStateFlow()
    
    private val _referrals = MutableStateFlow(0)
    val referrals: StateFlow<Int> = _referrals.asStateFlow()
    
    private val _wallet = MutableStateFlow(0.0)
    val wallet: StateFlow<Double> = _wallet.asStateFlow()
    
    private val _favoritesCount = MutableStateFlow(0)
    val favoritesCount: StateFlow<Int> = _favoritesCount.asStateFlow()
    
    private val _cardNumber = MutableStateFlow<String?>(null)
    val cardNumber: StateFlow<String?> = _cardNumber.asStateFlow()
    
    private val _cardType = MutableStateFlow<String?>(null)
    val cardType: StateFlow<String?> = _cardType.asStateFlow()
    
    private val _isCardActive = MutableStateFlow(false)
    val isCardActive: StateFlow<Boolean> = _isCardActive.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _referralCode = MutableStateFlow<String?>(null)
    val referralCode: StateFlow<String?> = _referralCode.asStateFlow()
    
    val referralQRCodeURL: String
        get() = _referralCode.value?.let { "https://allinconnect-form.vercel.app/?code=$it" } ?: ""
    
    init {
        loadData()
    }
    
    fun loadData(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            // Load user data
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
                },
                onFailure = {
                    _errorMessage.value = "Erreur lors du chargement des données utilisateur"
                }
            )
            
            // Load light user data for additional info
            profileRepository.getUserLight().fold(
                onSuccess = { userLight ->
                    _referrals.value = userLight.referralCount ?: 0
                    _wallet.value = userLight.walletBalance ?: 0.0
                    _referralCode.value = userLight.referralCode
                },
                onFailure = { }
            )
            
            // Load card data
            profileRepository.getCard().fold(
                onSuccess = { card ->
                    _cardNumber.value = card.cardNumber
                    _cardType.value = card.type
                    _isCardActive.value = true
                },
                onFailure = {
                    _isCardActive.value = false
                }
            )
            
            // Load savings
            savingsRepository.getSavings().fold(
                onSuccess = { savingsList ->
                    _savings.value = savingsList.sumOf { it.amount }
                },
                onFailure = { }
            )
            
            // Load favorites count
            favoritesRepository.getFavorites().fold(
                onSuccess = { favorites ->
                    _favoritesCount.value = favorites.size
                },
                onFailure = { }
            )
            
            _isLoading.value = false
        }
    }
    
    fun refreshData() {
        loadData(forceRefresh = true)
    }
}
