package com.allinconnect.app.presentation.partner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.allinconnect.app.presentation.components.FooterBar
import com.allinconnect.app.presentation.theme.AppGradient

@Composable
fun PartnerDetailScreen(
    partnerId: Int,
    onNavigateBack: () -> Unit,
    onNavigateToOfferDetail: ((Int) -> Unit)? = null,
    viewModel: PartnerDetailViewModel = hiltViewModel()
) {
    val partner by viewModel.partner.collectAsStateWithLifecycle()
    val offers by viewModel.offers.collectAsStateWithLifecycle()
    val reviews by viewModel.reviews.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    
    LaunchedEffect(partnerId) {
        viewModel.loadPartnerDetails(partnerId)
    }
    
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
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }
            
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    color = androidx.compose.ui.graphics.Color.White
                )
            } else if (partner != null) {
                Text(
                    text = partner!!.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = androidx.compose.ui.graphics.Color.White,
                    modifier = Modifier.padding(16.dp)
                )
                
                if (partner!!.city != null) {
                    Text(
                        text = partner!!.city!!,
                        style = MaterialTheme.typography.bodyMedium,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                
                if (partner!!.category != null) {
                    Text(
                        text = partner!!.category!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Offers section
                if (offers.isNotEmpty()) {
                    Text(
                        text = "Offres (${offers.size})",
                        style = MaterialTheme.typography.titleLarge,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    
                    offers.forEach { offer ->
                        com.allinconnect.app.presentation.components.OfferCard(
                            offer = offer,
                            onClick = {
                                offer.apiId?.let { onNavigateToOfferDetail?.invoke(it) }
                            }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Reviews section
                if (reviews.isNotEmpty()) {
                    Text(
                        text = "Avis (${reviews.size})",
                        style = MaterialTheme.typography.titleLarge,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    
                    reviews.forEach { review ->
                        com.allinconnect.app.presentation.components.ReviewCard(
                            review = review
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
