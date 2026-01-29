package com.allinconnect.app.core.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TutorialPreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences = 
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    
    fun hasSeenTutorial(): Boolean {
        return prefs.getBoolean("tutorial_completed", false)
    }
    
    fun completeTutorial() {
        prefs.edit().putBoolean("tutorial_completed", true).apply()
    }
}
