package com.allinconnect.app.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.allinconnect.app.core.preferences.TutorialPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val tutorialPreferencesManager: TutorialPreferencesManager
) : ViewModel() {
    
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()
    
    val totalPages = 4
    
    fun hasSeenTutorial(): Boolean {
        return tutorialPreferencesManager.hasSeenTutorial()
    }
    
    fun completeTutorial() {
        tutorialPreferencesManager.completeTutorial()
    }
    
    fun nextPage() {
        if (_currentPage.value < totalPages - 1) {
            _currentPage.value++
        }
    }
}
