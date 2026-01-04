package com.allinconnect.app.core.payment

import com.allinconnect.app.data.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentStatusManager @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    private val _pendingPaymentCheck = MutableStateFlow(false)
    val pendingPaymentCheck: StateFlow<Boolean> = _pendingPaymentCheck.asStateFlow()
    
    private val _lastPaymentStatus = MutableStateFlow<PaymentResultStatus?>(null)
    val lastPaymentStatus: StateFlow<PaymentResultStatus?> = _lastPaymentStatus.asStateFlow()
    
    enum class PaymentResultStatus {
        SUCCESS,
        FAILED,
        PENDING
    }
    
    suspend fun checkPaymentStatus(maxRetries: Int = 3): Boolean {
        _pendingPaymentCheck.value = true
        
        return try {
            val delayBetweenAttempts = 2000L // 2 secondes
            
            for (attempt in 0 until maxRetries) {
                val userMe = profileRepository.getUserMe()
                
                userMe.onSuccess { user ->
                    val isPremium = user.premiumEnabled == true
                    
                    if (isPremium) {
                        _lastPaymentStatus.value = PaymentResultStatus.SUCCESS
                        _pendingPaymentCheck.value = false
                        return true
                    } else {
                        if (attempt < maxRetries - 1) {
                            kotlinx.coroutines.delay(delayBetweenAttempts)
                        } else {
                            _lastPaymentStatus.value = PaymentResultStatus.PENDING
                            _pendingPaymentCheck.value = false
                            return false
                        }
                    }
                }.onFailure {
                    if (attempt < maxRetries - 1) {
                        kotlinx.coroutines.delay(delayBetweenAttempts)
                    } else {
                        _lastPaymentStatus.value = PaymentResultStatus.PENDING
                        _pendingPaymentCheck.value = false
                        return false
                    }
                }
            }
            
            false
        } finally {
            _pendingPaymentCheck.value = false
        }
    }
}

