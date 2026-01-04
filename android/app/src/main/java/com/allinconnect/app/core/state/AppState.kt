package com.allinconnect.app.core.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppState @Inject constructor() {
    var selectedTab: TabItem by mutableStateOf(TabItem.HOME)
        private set
    
    fun navigateToTab(tab: TabItem) {
        selectedTab = tab
    }
}

enum class TabItem {
    HOME,
    OFFERS,
    CARD,
    PROFILE
}

