package com.allinconnect.app.presentation.billing

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

@Composable
fun ManageSubscriptionScreen(
    onNavigateBack: () -> Unit,
    viewModel: ManageSubscriptionViewModel = hiltViewModel()
) {
    val subscriptionStatus by viewModel.subscriptionStatus.collectAsStateWithLifecycle()
    val portalUrl by viewModel.portalUrl.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val cancelSuccess by viewModel.cancelSuccess.collectAsStateWithLifecycle()
    
    var showCancelDialog by remember { mutableStateOf(false) }
    
    LaunchedEffect(portalUrl) {
        portalUrl?.let { url ->
            // TODO: Open URL in browser or WebView
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
                text = "Gérer mon abonnement",
                style = MaterialTheme.typography.headlineLarge,
                color = androidx.compose.ui.graphics.Color.White
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            if (errorMessage != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
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
            } else if (subscriptionStatus != null) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Statut de l'abonnement",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = if (subscriptionStatus!!.premiumEnabled) "Actif" else "Inactif",
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (subscriptionStatus!!.premiumEnabled) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.error
                            }
                        )
                        
                        subscriptionStatus!!.subscriptionStatus?.let { status ->
                            Text(
                                text = "Type: $status",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        
                        subscriptionStatus!!.currentPeriodEnd?.let { endDate ->
                            Text(
                                text = "Renouvellement: $endDate",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = { viewModel.openPortal() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                ) {
                    Text("Gérer dans le portail client")
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = { showCancelDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    enabled = !isLoading
                ) {
                    Text("Annuler l'abonnement")
                }
            }
        }
    }
    
    if (showCancelDialog) {
        AlertDialog(
            onDismissRequest = { showCancelDialog = false },
            title = { Text("Annuler l'abonnement") },
            text = { Text("Êtes-vous sûr de vouloir annuler votre abonnement ?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.cancelSubscription()
                        showCancelDialog = false
                    }
                ) {
                    Text("Confirmer")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCancelDialog = false }) {
                    Text("Annuler")
                }
            }
        )
    }
}

