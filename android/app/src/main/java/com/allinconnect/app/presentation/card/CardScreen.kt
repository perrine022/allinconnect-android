package com.allinconnect.app.presentation.card

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
fun CardScreen(
    onNavigateToDigitalCardInfo: (() -> Unit)? = null,
    onNavigateToFamilyCardManagement: (() -> Unit)? = null,
    onNavigateToSavingsList: (() -> Unit)? = null,
    viewModel: CardViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val savings by viewModel.savings.collectAsStateWithLifecycle()
    val referrals by viewModel.referrals.collectAsStateWithLifecycle()
    val wallet by viewModel.wallet.collectAsStateWithLifecycle()
    val favoritesCount by viewModel.favoritesCount.collectAsStateWithLifecycle()
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val cardType by viewModel.cardType.collectAsStateWithLifecycle()
    val isCardActive by viewModel.isCardActive.collectAsStateWithLifecycle()
    val referralCode by viewModel.referralCode.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    
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
                text = "Ma Carte",
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
            } else if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(20.dp)
                )
            } else {
                Spacer(modifier = Modifier.height(20.dp))
                
                // Card info
                if (cardNumber != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                            .clickable { onNavigateToDigitalCardInfo?.invoke() }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Carte ${cardType ?: "CLUB10"}",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                )
                                Text(
                                    text = "N° ${cardNumber}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = if (isCardActive) "Active" else "Inactive",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (isCardActive) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = "Voir détails",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
                
                // Stats
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Statistiques",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // Savings - clickable
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNavigateToSavingsList?.invoke() }
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Économies totales: ${String.format("%.2f", savings)}€",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = "Voir économies",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        
                        Text(
                            text = "Parrainages: $referrals",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Portefeuille: ${String.format("%.2f", wallet)}€",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Favoris: $favoritesCount",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                
                // Menu actions
                Spacer(modifier = Modifier.height(16.dp))
                
                // Family card management
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                        .clickable { onNavigateToFamilyCardManagement?.invoke() }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.People,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Gérer la carte famille",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Voir",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                
                // Referral code
                if (referralCode != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Code de parrainage",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = referralCode!!,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        
        FooterBar(
            selectedTab = remember { mutableStateOf(com.allinconnect.app.core.state.TabItem.CARD) },
            onTabSelected = {},
            modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter)
        )
    }
}
