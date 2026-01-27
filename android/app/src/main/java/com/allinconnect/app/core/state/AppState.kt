package com.allinconnect.app.core.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class TabItem {
    HOME,
    OFFERS,
    CARD,
    PROFILE
}

class AppState {
    var selectedTab by mutableStateOf(TabItem.HOME)
    var showProfileBadge by mutableStateOf(false)
    
    fun navigateToTab(tab: TabItem) {
        selectedTab = tab
    }
}
