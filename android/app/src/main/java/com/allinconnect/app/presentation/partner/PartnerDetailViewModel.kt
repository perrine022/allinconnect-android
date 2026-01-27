package com.allinconnect.app.presentation.partner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.data.mapper.RatingMapper
import com.allinconnect.app.data.repository.FavoritesRepository
import com.allinconnect.app.data.repository.OffersRepository
import com.allinconnect.app.data.repository.PartnersRepository
import com.allinconnect.app.data.repository.RatingsRepository
import com.allinconnect.app.domain.model.Offer
import com.allinconnect.app.domain.model.Partner
import com.allinconnect.app.domain.model.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnerDetailViewModel @Inject constructor(
    private val partnersRepository: PartnersRepository,
    private val favoritesRepository: FavoritesRepository,
    private val offersRepository: OffersRepository,
    private val ratingsRepository: RatingsRepository
) : ViewModel() {
    
    private val _partner = MutableStateFlow<Partner?>(null)
    val partner: StateFlow<Partner?> = _partner.asStateFlow()
    
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers.asStateFlow()
    
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    fun loadPartnerDetails(partnerId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            
            partnersRepository.getProfessionalById(partnerId).fold(
                onSuccess = { partner ->
                    _partner.value = partner
                    
                    // Load offers for this professional
                    offersRepository.getOffers().fold(
                        onSuccess = { allOffers ->
                            // Filter offers by professionalId
                            _offers.value = allOffers.filter { 
                                it.partnerId?.toIntOrNull() == partnerId 
                            }
                        },
                        onFailure = { }
                    )
                    
                    // Load reviews/ratings
                    ratingsRepository.getRatings(partnerId).fold(
                        onSuccess = { ratings ->
                            _reviews.value = ratings.map { RatingMapper.toDomain(it) }
                        },
                        onFailure = { }
                    )
                    
                    _isLoading.value = false
                },
                onFailure = {
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun toggleFavorite(partnerId: Int) {
        viewModelScope.launch {
            _partner.value?.let { partner ->
                if (partner.isFavorite) {
                    favoritesRepository.removeFavorite(partnerId)
                } else {
                    favoritesRepository.addFavorite(partnerId)
                }
                _partner.value = partner.copy(isFavorite = !partner.isFavorite)
            }
        }
    }
}
