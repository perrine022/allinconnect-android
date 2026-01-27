package com.allinconnect.app.presentation.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.allinconnect.app.core.state.AppState
import com.allinconnect.app.core.state.TabItem
import com.allinconnect.app.presentation.card.CardScreen
import com.allinconnect.app.presentation.components.FooterBar
import com.allinconnect.app.presentation.home.HomeScreen
import com.allinconnect.app.presentation.offers.OffersScreen
import com.allinconnect.app.presentation.profile.ProfileScreen

@Composable
fun TabBarScreen(
    onNavigateToOfferDetail: ((Int) -> Unit)? = null,
    onNavigateToPartnerDetail: ((Int) -> Unit)? = null,
    onNavigateToEditProfile: (() -> Unit)? = null,
    onNavigateToSettings: (() -> Unit)? = null,
    onNavigateToNotificationPreferences: (() -> Unit)? = null,
    onNavigateToHelpSupport: (() -> Unit)? = null,
    onNavigateToTerms: (() -> Unit)? = null,
    onNavigateToChangePassword: (() -> Unit)? = null,
    onNavigateToReferrals: (() -> Unit)? = null,
    onNavigateToDigitalCardInfo: (() -> Unit)? = null,
    onNavigateToFamilyCardManagement: (() -> Unit)? = null,
    onNavigateToSavingsList: (() -> Unit)? = null
) {
    val appState = remember { AppState() }
    val selectedTab = remember { mutableStateOf(appState.selectedTab) }
    
    Box(modifier = Modifier.fillMaxSize()) {
        when (selectedTab.value) {
            TabItem.HOME -> HomeScreen(
                onNavigateToOfferDetail = onNavigateToOfferDetail,
                onNavigateToPartnerDetail = onNavigateToPartnerDetail
            )
            TabItem.OFFERS -> OffersScreen(
                onNavigateToOfferDetail = onNavigateToOfferDetail
            )
            TabItem.CARD -> CardScreen(
                onNavigateToDigitalCardInfo = { onNavigateToDigitalCardInfo?.invoke() },
                onNavigateToFamilyCardManagement = { onNavigateToFamilyCardManagement?.invoke() },
                onNavigateToSavingsList = { onNavigateToSavingsList?.invoke() }
            )
            TabItem.PROFILE -> ProfileScreen(
                onNavigateToEditProfile = { onNavigateToEditProfile?.invoke() },
                onNavigateToSettings = { onNavigateToSettings?.invoke() },
                onNavigateToNotificationPreferences = { onNavigateToNotificationPreferences?.invoke() },
                onNavigateToHelpSupport = { onNavigateToHelpSupport?.invoke() },
                onNavigateToTerms = { onNavigateToTerms?.invoke() },
                onNavigateToChangePassword = { onNavigateToChangePassword?.invoke() },
                onNavigateToReferrals = { onNavigateToReferrals?.invoke() }
            )
        }
        
        FooterBar(
            selectedTab = selectedTab,
            onTabSelected = { tab ->
                appState.navigateToTab(tab)
                selectedTab.value = tab
            },
            modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter)
        )
    }
}
