package com.allinconnect.app.core.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

@Singleton
class AuthTokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val tokenKey = stringPreferencesKey("auth_token")
    
    // In-memory cache for synchronous access (used in interceptors)
    @Volatile
    private var cachedToken: String? = null
    
    val token: Flow<String?>
        get() = context.dataStore.data.map { preferences ->
            val token = preferences[tokenKey]
            cachedToken = token // Mettre à jour le cache à chaque changement
            token
        }
    
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
        cachedToken = token
    }
    
    suspend fun getToken(): String? {
        return context.dataStore.data.first()[tokenKey]
    }
    
    // For synchronous access in interceptors (use with caution)
    fun getTokenSync(): String? {
        return cachedToken
    }
    
    
    suspend fun removeToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(tokenKey)
        }
        cachedToken = null
    }
    
    suspend fun hasToken(): Boolean {
        return getToken() != null
    }
}

