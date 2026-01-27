package com.allinconnect.app.core.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

data class CacheEntry<T>(
    val data: T,
    val timestamp: Instant,
    val expirationTime: Long // milliseconds
) {
    fun isExpired(): Boolean {
        return Instant.now().toEpochMilli() - timestamp.toEpochMilli() > expirationTime
    }
}

@Singleton
class CacheService @Inject constructor(
    private val context: Context
) {
    private val prefs: SharedPreferences = 
        context.getSharedPreferences("cache_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val cachePrefix = "cache_"
    
    object CacheDuration {
        const val OFFERS = 300_000L // 5 minutes in milliseconds
        const val PROFILE = 600_000L // 10 minutes
        const val CARD = 300_000L // 5 minutes
    }
    
    suspend fun <T> save(data: T, key: String, expirationTime: Long) {
        withContext(Dispatchers.IO) {
            val entry = CacheEntry(
                data = data,
                timestamp = Instant.now(),
                expirationTime = expirationTime
            )
            val json = gson.toJson(entry)
            prefs.edit().putString("$cachePrefix$key", json).apply()
        }
    }
    
    suspend fun <T> get(type: Class<T>, key: String): T? {
        return withContext(Dispatchers.IO) {
            val json = prefs.getString("$cachePrefix$key", null) ?: return@withContext null
            try {
                val typeToken = TypeToken.getParameterized(CacheEntry::class.java, type)
                val entry: CacheEntry<T> = gson.fromJson(json, typeToken.type)
                if (entry.isExpired()) {
                    remove(key)
                    null
                } else {
                    entry.data
                }
            } catch (e: Exception) {
                null
            }
        }
    }
    
    fun hasValidCache(key: String): Boolean {
        val json = prefs.getString("$cachePrefix$key", null) ?: return false
        return try {
            val entry = gson.fromJson(json, CacheEntry::class.java)
            !entry.isExpired()
        } catch (e: Exception) {
            false
        }
    }
    
    fun remove(key: String) {
        prefs.edit().remove("$cachePrefix$key").apply()
    }
    
    fun clearAll() {
        prefs.edit().clear().apply()
    }
}
