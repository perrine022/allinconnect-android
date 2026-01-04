package com.allinconnect.app.presentation.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.allinconnect.app.presentation.home.HomeScreen
import com.allinconnect.app.presentation.offers.OffersScreen
import com.allinconnect.app.presentation.card.CardScreen
import com.allinconnect.app.presentation.profile.ProfileScreen

@Composable
fun TabBarScreen() {
    var selectedTab by remember { mutableStateOf(TabItem.Home) }
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                TabItem.values().forEach { tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title) },
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                TabItem.Home -> HomeScreen()
                TabItem.Offers -> OffersScreen()
                TabItem.Card -> CardScreen()
                TabItem.Profile -> ProfileScreen()
            }
        }
    }
}

enum class TabItem(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Home("Accueil", Icons.Default.Home),
    Offers("Offres", Icons.Default.LocalOffer),
    Card("Ma Carte", Icons.Default.CreditCard),
    Profile("Profil", Icons.Default.Person)
}

