package com.allinconnect.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.allinconnect.app.presentation.components.FooterBar
import com.allinconnect.app.presentation.theme.AppGradient

@Composable
fun HomeScreen(
    onNavigateToOfferDetail: ((Int) -> Unit)? = null,
    onNavigateToPartnerDetail: ((Int) -> Unit)? = null,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val offers by viewModel.offers.collectAsStateWithLifecycle()
    val partners by viewModel.partners.collectAsStateWithLifecycle()
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
                text = "Trouve ton partenaire ALL IN près de chez toi",
                style = MaterialTheme.typography.headlineMedium,
                color = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    color = androidx.compose.ui.graphics.Color.White
                )
            } else {
                // Offers section
                if (offers.isNotEmpty()) {
                    Text(
                        text = "À ne pas louper",
                        style = MaterialTheme.typography.titleLarge,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                    )
                    
                    offers.take(5).forEach { offer ->
                        com.allinconnect.app.presentation.components.OfferCard(
                            offer = offer,
                            onClick = {
                                offer.apiId?.let { onNavigateToOfferDetail?.invoke(it) }
                            }
                        )
                    }
                }
                
                // Partners section
                if (partners.isNotEmpty()) {
                    Text(
                        text = "Les partenaires",
                        style = MaterialTheme.typography.titleLarge,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                    )
                    
                    partners.take(5).forEach { partner ->
                        com.allinconnect.app.presentation.components.PartnerCard(
                            partner = partner,
                            onClick = {
                                partner.apiId?.let { onNavigateToPartnerDetail?.invoke(it) }
                            }
                        )
                    }
                }
            }
        }
        
        FooterBar(
            selectedTab = remember { mutableStateOf(com.allinconnect.app.core.state.TabItem.HOME) },
            onTabSelected = {},
            modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter)
        )
    }
}
