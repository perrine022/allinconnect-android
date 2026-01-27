package com.allinconnect.app.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.allinconnect.app.presentation.components.FooterBar
import com.allinconnect.app.presentation.theme.AppGradient

@Composable
fun ProfileScreen(
    onNavigateToEditProfile: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToNotificationPreferences: () -> Unit = {},
    onNavigateToHelpSupport: () -> Unit = {},
    onNavigateToTerms: () -> Unit = {},
    onNavigateToChangePassword: () -> Unit = {},
    onNavigateToReferrals: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "Profil",
                style = MaterialTheme.typography.headlineLarge,
                color = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    color = androidx.compose.ui.graphics.Color.White
                )
            } else if (user != null) {
                Spacer(modifier = Modifier.height(20.dp))
                
                // User info card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "${user!!.firstName} ${user!!.lastName}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Text(
                            text = user!!.username,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = if (user!!.userType == com.allinconnect.app.domain.model.UserType.PRO) "Professionnel" else "Client",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                
                // Menu items
                Spacer(modifier = Modifier.height(16.dp))
                
                androidx.compose.material3.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    androidx.compose.material3.ListItem(
                        headlineContent = { Text("Modifier le profil") },
                        leadingContent = {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Edit,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.clickable { onNavigateToEditProfile() }
                    )
                }
                
                androidx.compose.material3.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    androidx.compose.material3.ListItem(
                        headlineContent = { Text("Paramètres") },
                        leadingContent = {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Settings,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.clickable { onNavigateToSettings() }
                    )
                }
                
                androidx.compose.material3.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    androidx.compose.material3.ListItem(
                        headlineContent = { Text("Notifications") },
                        leadingContent = {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Notifications,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.clickable { onNavigateToNotificationPreferences() }
                    )
                }
                
                androidx.compose.material3.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    androidx.compose.material3.ListItem(
                        headlineContent = { Text("Aide & Support") },
                        leadingContent = {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Help,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.clickable { onNavigateToHelpSupport() }
                    )
                }
                
                androidx.compose.material3.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    androidx.compose.material3.ListItem(
                        headlineContent = { Text("CGU") },
                        leadingContent = {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Description,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.clickable { onNavigateToTerms() }
                    )
                }
                
                androidx.compose.material3.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    androidx.compose.material3.ListItem(
                        headlineContent = { Text("Changer le mot de passe") },
                        leadingContent = {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Lock,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.clickable { onNavigateToChangePassword() }
                    )
                }
                
                androidx.compose.material3.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    androidx.compose.material3.ListItem(
                        headlineContent = { Text("Parrainage") },
                        leadingContent = {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.PersonAdd,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.clickable { onNavigateToReferrals() }
                    )
                }
            }
        }
        
        FooterBar(
            selectedTab = remember { mutableStateOf(com.allinconnect.app.core.state.TabItem.PROFILE) },
            onTabSelected = {},
            modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter)
        )
    }
}
