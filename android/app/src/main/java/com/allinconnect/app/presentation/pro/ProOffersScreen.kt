package com.allinconnect.app.presentation.pro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.allinconnect.app.presentation.components.OfferCard
import com.allinconnect.app.presentation.theme.AppGradient

@Composable
fun ProOffersScreen(
    onNavigateBack: () -> Unit,
    onNavigateToCreateOffer: (() -> Unit)? = null,
    viewModel: ProOffersViewModel = hiltViewModel()
) {
    val offers by viewModel.offers.collectAsStateWithLifecycle()
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
                .padding(20.dp)
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = "Mes offres",
                    style = MaterialTheme.typography.headlineLarge,
                    color = androidx.compose.ui.graphics.Color.White
                )
                
                onNavigateToCreateOffer?.let {
                    IconButton(onClick = it) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Créer une offre",
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                }
            }
            
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
            } else {
                if (offers.isEmpty()) {
                    Text(
                        text = "Aucune offre créée",
                        style = MaterialTheme.typography.bodyLarge,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                    
                    onNavigateToCreateOffer?.let {
                        Button(
                            onClick = it,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Créer ma première offre")
                        }
                    }
                } else {
                    offers.forEach { offer ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = offer.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Text(
                                    text = offer.description,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Réduction: ${offer.discount}",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                    
                                    Text(
                                        text = "Valide jusqu'au ${offer.validUntil}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Row {
                                    TextButton(onClick = { /* TODO: Navigate to edit */ }) {
                                        Icon(Icons.Default.Edit, contentDescription = null)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Modifier")
                                    }
                                    
                                    Spacer(modifier = Modifier.width(8.dp))
                                    
                                    TextButton(
                                        onClick = { viewModel.archiveOffer(offer.apiId ?: 0) }
                                    ) {
                                        Text("Archiver")
                                    }
                                    
                                    Spacer(modifier = Modifier.width(8.dp))
                                    
                                    TextButton(
                                        onClick = { viewModel.deleteOffer(offer.apiId ?: 0) },
                                        colors = ButtonDefaults.textButtonColors(
                                            contentColor = MaterialTheme.colorScheme.error
                                        )
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = null)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Supprimer")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
