package com.allinconnect.app.presentation.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinconnect.app.core.network.ApiError
import com.allinconnect.app.data.dto.subscription.CardMember
import com.allinconnect.app.data.repository.SubscriptionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FamilyCardManagementViewModel @Inject constructor(
    private val subscriptionsRepository: SubscriptionsRepository
) : ViewModel() {
    
    private val _activeMembers = MutableStateFlow<List<CardMember>>(emptyList())
    val activeMembers: StateFlow<List<CardMember>> = _activeMembers.asStateFlow()
    
    private val _pendingInvitations = MutableStateFlow<List<String>>(emptyList())
    val pendingInvitations: StateFlow<List<String>> = _pendingInvitations.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _inviteSuccess = MutableStateFlow(false)
    val inviteSuccess: StateFlow<Boolean> = _inviteSuccess.asStateFlow()
    
    init {
        loadCardMembers()
    }
    
    fun loadCardMembers() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            subscriptionsRepository.getCardMembers().fold(
                onSuccess = { response ->
                    _activeMembers.value = response.activeMembers
                    _pendingInvitations.value = response.pendingInvitations
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = when (error) {
                        is ApiError.NetworkError -> "Problème de connexion"
                        else -> "Erreur lors du chargement"
                    }
                    _isLoading.value = false
                }
            )
        }
    }
    
    fun inviteMember(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            // TODO: Implémenter l'appel API pour inviter un membre
            // subscriptionsRepository.inviteCardMember(email).fold(...)
            
            // Pour l'instant, on simule
            _inviteSuccess.value = true
            _isLoading.value = false
            loadCardMembers()
        }
    }
    
    fun removeMember(memberId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            // TODO: Implémenter l'appel API pour supprimer un membre
            // subscriptionsRepository.removeCardMember(memberId).fold(...)
            
            _isLoading.value = false
            loadCardMembers()
        }
    }
    
    fun refresh() {
        loadCardMembers()
    }
}
