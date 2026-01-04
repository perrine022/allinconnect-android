package com.allinconnect.app.core.notifications

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.allinconnect.app.core.auth.AuthTokenManager
import com.allinconnect.app.data.api.PushApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

private val Context.pushDataStore: androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "push_prefs")

@Singleton
class PushManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authTokenManager: AuthTokenManager,
    private val pushApi: PushApi
) {
    private val fcmTokenKey = stringPreferencesKey("fcm_token")
    
    suspend fun registerFCMToken(fcmToken: String) {
        // Stocker le token FCM
        context.pushDataStore.edit { preferences ->
            preferences[fcmTokenKey] = fcmToken
        }
        
        // Enregistrer le token si l'utilisateur est connecté
        if (authTokenManager.hasToken()) {
            registerTokenWithBackend(fcmToken)
        }
    }
    
    suspend fun registerTokenAfterLogin() {
        val token = context.pushDataStore.data.first()[fcmTokenKey]
        if (token != null) {
            registerTokenWithBackend(token)
        }
    }
    
    suspend fun unregisterToken() {
        // Le token reste stocké localement mais ne sera plus associé à un utilisateur
    }
    
    private suspend fun registerTokenWithBackend(token: String) {
        if (!authTokenManager.hasToken()) {
            return
        }
        
        val environment = if (com.allinconnect.app.BuildConfig.DEBUG) "SANDBOX" else "PRODUCTION"
        
        try {
            pushApi.registerToken(
                RegisterTokenRequest(
                    token = token,
                    platform = "ANDROID",
                    environment = environment
                )
            )
        } catch (e: Exception) {
            // Log error
        }
    }
}

