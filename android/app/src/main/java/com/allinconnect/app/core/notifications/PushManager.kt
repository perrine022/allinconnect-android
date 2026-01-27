package com.allinconnect.app.core.notifications

import com.allinconnect.app.core.auth.AuthTokenManager
import com.allinconnect.app.data.api.PushApi
import com.allinconnect.app.data.dto.push.RegisterTokenRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PushManager @Inject constructor(
    private val pushApi: PushApi,
    private val authTokenManager: AuthTokenManager
) {
    private val _deviceToken = MutableStateFlow<String?>(null)
    val deviceToken: StateFlow<String?> = _deviceToken.asStateFlow()
    
    fun handleDeviceToken(token: String) {
        _deviceToken.value = token
        // Register token if user is logged in
        if (authTokenManager.getTokenSync() != null) {
            registerTokenWithBackend(token)
        }
    }
    
    suspend fun registerTokenAfterLogin() {
        _deviceToken.value?.let { token ->
            registerTokenWithBackend(token)
        }
    }
    
    fun unregisterToken() {
        // Token remains stored but no longer associated with user
    }
    
    private suspend fun registerTokenWithBackend(token: String) {
        try {
            pushApi.registerToken(RegisterTokenRequest(token))
        } catch (e: Exception) {
            // Handle error
        }
    }
}
