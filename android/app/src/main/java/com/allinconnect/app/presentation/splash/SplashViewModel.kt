package com.allinconnect.app.presentation.splash

import androidx.lifecycle.ViewModel
import com.allinconnect.app.core.auth.AuthTokenManager
import com.allinconnect.app.presentation.onboarding.TutorialViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authTokenManager: AuthTokenManager,
    private val tutorialViewModel: TutorialViewModel
) : ViewModel() {
    
    suspend fun shouldShowTutorial(): Boolean {
        return !tutorialViewModel.hasSeenTutorial()
    }
    
    suspend fun isLoggedIn(): Boolean {
        return authTokenManager.getToken() != null
    }
}
