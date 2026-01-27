package com.allinconnect.app.presentation.pro

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
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun InvoicesScreen(
    onNavigateBack: () -> Unit,
    viewModel: InvoicesViewModel = hiltViewModel()
) {
    val invoices by viewModel.invoices.collectAsStateWithLifecycle()
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
            
            Text(
                text = "Factures",
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
            } else {
                if (invoices.isEmpty()) {
                    Text(
                        text = "Aucune facture disponible",
                        style = MaterialTheme.typography.bodyLarge,
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                } else {
                    invoices.forEach { invoice ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = invoice.number ?: "Facture #${invoice.id}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    
                                    Text(
                                        text = "${invoice.amountPaid / 100.0} ${invoice.currency.uppercase()}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Text(
                                    text = "Statut: ${invoice.status}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                
                                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                val date = Date(invoice.created * 1000)
                                Text(
                                    text = "Date: ${dateFormat.format(date)}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                
                                if (invoice.hostedInvoiceUrl != null) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    TextButton(onClick = { /* TODO: Open URL */ }) {
                                        Text("Voir la facture")
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
