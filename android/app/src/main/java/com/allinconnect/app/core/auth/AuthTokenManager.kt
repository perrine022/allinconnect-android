package com.allinconnect.app.core.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

@Singleton
class AuthTokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val tokenKey = stringPreferencesKey("auth_token")
    
    // Cache in-memory pour accès synchrone
    @Volatile
    private var cachedToken: String? = null
    
    val tokenFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[tokenKey].also { cachedToken = it }
    }
    
    suspend fun getToken(): String? {
        return cachedToken ?: context.dataStore.data.first()[tokenKey].also { cachedToken = it }
    }
    
    fun getTokenSync(): String? = cachedToken
    
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
        cachedToken = token
    }
    
    suspend fun removeToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(tokenKey)
        }
        cachedToken = null
    }
}
