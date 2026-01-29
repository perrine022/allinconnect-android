package com.allinconnect.app.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.allinconnect.app.presentation.theme.AppGradient
import com.allinconnect.app.presentation.theme.AppGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSubscriptionScreen(
    onNavigateBack: () -> Unit,
    onNavigateToTabBar: () -> Unit,
    onNavigateToPaymentSheet: (String?, String?, String?, String?) -> Unit,
    viewModel: ClientSubscriptionViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val plans by viewModel.plans.collectAsStateWithLifecycle()
    val selectedPlan by viewModel.selectedPlan.collectAsStateWithLifecycle()
    val paymentSheetData by viewModel.paymentSheetData.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    
    LaunchedEffect(paymentSheetData) {
        paymentSheetData?.let { data ->
            onNavigateToPaymentSheet(
                data.paymentIntent,
                data.customerId,
                data.ephemeralKey,
                data.publishableKey
            )
        }
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
                .padding(20.dp)
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }
            
            Text(
                text = "Choisis ton abonnement",
                style = MaterialTheme.typography.headlineLarge,
                color = androidx.compose.ui.graphics.Color.White
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            if (errorMessage != null) {
                androidx.compose.material3.Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    color = androidx.compose.ui.graphics.Color.White
                )
            } else {
                if (plans.isEmpty()) {
                    Text(
                        text = "Aucun plan disponible",
                        style = MaterialTheme.typography.bodyLarge,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                    
                    Button(
                        onClick = onNavigateToTabBar,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = AppGold)
                    ) {
                        Text("Continuer sans abonnement", color = androidx.compose.ui.graphics.Color.Black)
                    }
                } else {
                    plans.forEach { plan ->
                        androidx.compose.material3.Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = { viewModel.selectPlan(plan) },
                            colors = androidx.compose.material3.CardDefaults.cardColors(
                                containerColor = if (selectedPlan?.id == plan.id) {
                                    MaterialTheme.colorScheme.primaryContainer
                                } else {
                                    MaterialTheme.colorScheme.surface
                                }
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = plan.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                )
                                
                                if (plan.description != null) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = plan.description!!,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${String.format("%.2f", plan.price)}€",
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    
                                    if (plan.duration != null) {
                                        Text(
                                            text = plan.duration,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Button(
                        onClick = {
                            selectedPlan?.id?.let { viewModel.subscribeToPlan(it) }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = AppGold),
                        enabled = selectedPlan != null && !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = androidx.compose.ui.graphics.Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text("S'abonner", color = androidx.compose.ui.graphics.Color.Black)
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    TextButton(
                        onClick = onNavigateToTabBar,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Continuer sans abonnement", color = androidx.compose.ui.graphics.Color.White)
                    }
                }
            }
        }
    }
}
