package com.allinconnect.app.presentation.offers

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
fun OffersScreen(
    onNavigateToOfferDetail: ((Int) -> Unit)? = null,
    viewModel: OffersViewModel = hiltViewModel()
) {
    val offers by viewModel.offers.collectAsStateWithLifecycle()
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
                text = "Offres & Événements",
                style = MaterialTheme.typography.headlineLarge,
                color = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    color = androidx.compose.ui.graphics.Color.White
                )
            } else {
                if (offers.isEmpty()) {
                    Text(
                        text = "Aucune offre disponible",
                        style = MaterialTheme.typography.bodyLarge,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(20.dp)
                    )
                } else {
                    offers.forEach { offer ->
                        com.allinconnect.app.presentation.components.OfferCard(
                            offer = offer,
                            onClick = {
                                offer.apiId?.let { onNavigateToOfferDetail?.invoke(it) }
                            }
                        )
                    }
                }
            }
        }
        
        FooterBar(
            selectedTab = remember { mutableStateOf(com.allinconnect.app.core.state.TabItem.OFFERS) },
            onTabSelected = {},
            modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter)
        )
    }
}
