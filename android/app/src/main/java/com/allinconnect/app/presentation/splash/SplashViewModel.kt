package com.allinconnect.app.presentation.splash

import androidx.lifecycle.ViewModel
import com.allinconnect.app.core.auth.AuthTokenManager
import com.allinconnect.app.core.preferences.TutorialPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authTokenManager: AuthTokenManager,
    private val tutorialPreferencesManager: TutorialPreferencesManager
) : ViewModel() {
    
    suspend fun shouldShowTutorial(): Boolean {
        return !tutorialPreferencesManager.hasSeenTutorial()
    }
    
    suspend fun isLoggedIn(): Boolean {
        return authTokenManager.getToken() != null
    }
}
