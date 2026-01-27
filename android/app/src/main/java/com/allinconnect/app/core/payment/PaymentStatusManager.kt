package com.allinconnect.app.core.payment

import com.allinconnect.app.data.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

enum class PaymentResultStatus {
    SUCCESS,
    FAILED,
    PENDING
}

@Singleton
class PaymentStatusManager @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    private val _pendingPaymentCheck = MutableStateFlow(false)
    val pendingPaymentCheck: StateFlow<Boolean> = _pendingPaymentCheck.asStateFlow()
    
    private val _lastPaymentStatus = MutableStateFlow<PaymentResultStatus?>(null)
    val lastPaymentStatus: StateFlow<PaymentResultStatus?> = _lastPaymentStatus.asStateFlow()
    
    suspend fun checkPaymentStatus(maxRetries: Int = 3): Boolean {
        _pendingPaymentCheck.value = true
        
        return try {
            var success = false
            repeat(maxRetries) { attempt ->
                val result = profileRepository.getUserMe()
                result.fold(
                    onSuccess = { userMe ->
                        // Check if premium is enabled
                        // TODO: Add premiumEnabled field to UserMeResponse
                        if (attempt == maxRetries - 1) {
                            _lastPaymentStatus.value = PaymentResultStatus.SUCCESS
                            success = true
                        }
                    },
                    onFailure = {
                        if (attempt < maxRetries - 1) {
                            kotlinx.coroutines.delay(2000)
                        } else {
                            _lastPaymentStatus.value = PaymentResultStatus.PENDING
                        }
                    }
                )
            }
            success
        } finally {
            _pendingPaymentCheck.value = false
        }
    }
}
