package com.allinconnect.app.core.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.allinconnect.app.data.dto.profile.UserLightResponse
import com.allinconnect.app.domain.model.Offer
import kotlinx.coroutines.flow.first
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

private val Context.cacheDataStore: DataStore<Preferences> by preferencesDataStore(name = "cache_prefs")

@Serializable
data class CacheEntry<T>(
    val data: String, // JSON string
    val timestamp: Long,
    val expirationTime: Long
) {
    fun isExpired(): Boolean {
        return System.currentTimeMillis() - timestamp > expirationTime
    }
}

@Singleton
class CacheService @Inject constructor(
    private val context: Context,
    private val json: Json
) {
    private val cachePrefix = "cache_"
    
    object CacheDuration {
        const val OFFERS = 300_000L // 5 minutes en millisecondes
        const val PROFILE = 600_000L // 10 minutes
        const val CARD = 300_000L // 5 minutes
    }
    
    suspend inline fun <reified T> save(data: T, key: String, expirationTime: Long) {
        val dataKey = stringPreferencesKey("$cachePrefix$key")
        val timestamp = System.currentTimeMillis()
        
        val jsonString = json.encodeToString(data)
        val entry = CacheEntry(
            data = jsonString,
            timestamp = timestamp,
            expirationTime = expirationTime
        )
        
        val entryJson = json.encodeToString(entry)
        
        context.cacheDataStore.edit { preferences ->
            preferences[dataKey] = entryJson
        }
    }
    
    suspend inline fun <reified T> get(key: String): T? {
        val dataKey = stringPreferencesKey("$cachePrefix$key")
        
        val entryJson = context.cacheDataStore.data.first()[dataKey] ?: return null
        
        val entry = json.decodeFromString<CacheEntry<String>>(entryJson)
        
        if (entry.isExpired()) {
            remove(key)
            return null
        }
        
        return json.decodeFromString<T>(entry.data)
    }
    
    suspend fun hasValidCache(key: String): Boolean {
        val dataKey = stringPreferencesKey("$cachePrefix$key")
        val entryJson = context.cacheDataStore.data.first()[dataKey] ?: return false
        
        return try {
            val entry = json.decodeFromString<CacheEntry<String>>(entryJson)
            !entry.isExpired()
        } catch (e: Exception) {
            false
        }
    }
    
    suspend fun remove(key: String) {
        val dataKey = stringPreferencesKey("$cachePrefix$key")
        context.cacheDataStore.edit { preferences ->
            preferences.remove(dataKey)
        }
    }
    
    suspend fun clearAll() {
        // Note: DataStore ne permet pas de supprimer toutes les clés facilement
        // Il faudrait garder une liste des clés utilisées
    }
    
    // Specific methods
    suspend fun saveOffers(offers: List<Offer>) {
        save(offers, "offers", CacheDuration.OFFERS)
    }
    
    suspend fun getOffers(): List<Offer>? {
        return get<List<Offer>>("offers")
    }
    
    suspend fun saveProfile(userLight: UserLightResponse) {
        save(userLight, "profile", CacheDuration.PROFILE)
    }
    
    suspend fun getProfile(): UserLightResponse? {
        return get<UserLightResponse>("profile")
    }
}

