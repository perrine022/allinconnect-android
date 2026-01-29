package com.allinconnect.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.allinconnect.app.core.state.TabItem
import com.allinconnect.app.presentation.theme.AppDarkRed1
import com.allinconnect.app.presentation.theme.AppDarkRed2
import com.allinconnect.app.presentation.theme.AppRed

@Composable
fun FooterBar(
    selectedTab: State<TabItem>,
    onTabSelected: (TabItem) -> Unit,
    modifier: Modifier = Modifier,
    showProfileBadge: Boolean = false,
    isLoggedIn: Boolean = true
) {
    val tabs = listOf(
        TabItem.HOME to ("Accueil" to Icons.Default.Home),
        TabItem.OFFERS to ("Offres" to Icons.Default.LocalOffer),
        TabItem.CARD to ("Ma Carte" to Icons.Default.CreditCard),
        TabItem.PROFILE to ("Profil" to Icons.Default.Person)
    )
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(AppDarkRed1, AppDarkRed2)
                )
            )
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tabPair ->
                val tab = tabPair.first
                val label = tabPair.second.first
                val icon = tabPair.second.second
                val isAccessible = tab == TabItem.HOME || isLoggedIn
                val isSelected = selectedTab.value == tab
                
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .alpha(if (isAccessible) 1f else 0.5f)
                    ) {
                        IconButton(
                            onClick = {
                                if (isAccessible) {
                                    onTabSelected(tab)
                                }
                            },
                            enabled = isAccessible
                        ) {
                            Box {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = label,
                                    tint = if (isAccessible) {
                                        if (isSelected) AppRed else Color(0.7f, 0.7f, 0.7f)
                                    } else {
                                        Color(0.4f, 0.4f, 0.4f)
                                    }
                                )
                                
                                if (tab == TabItem.PROFILE && showProfileBadge) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .offset(x = 8.dp, y = (-2).dp)
                                            .size(8.dp)
                                            .background(AppRed, shape = androidx.compose.foundation.shape.CircleShape)
                                    )
                                }
                            }
                        }
                        
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isAccessible) {
                                if (isSelected) AppRed else Color(0.7f, 0.7f, 0.7f)
                            } else {
                                Color(0.4f, 0.4f, 0.4f)
                            }
                        )
                    }
                }
            }
        }
    }
}
