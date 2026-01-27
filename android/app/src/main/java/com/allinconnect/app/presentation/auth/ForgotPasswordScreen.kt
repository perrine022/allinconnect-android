package com.allinconnect.app.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.allinconnect.app.presentation.theme.AppGradient
import com.allinconnect.app.presentation.theme.AppGold

@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isEmailSent by viewModel.isEmailSent.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val isValid = viewModel.isValid
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            if (!isEmailSent) {
                // Lock icon
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Lock,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    tint = androidx.compose.ui.graphics.Color.White
                )
                
                Spacer(modifier = Modifier.height(30.dp))
                
                Text(
                    text = "Mot de passe oublié",
                    style = MaterialTheme.typography.headlineLarge,
                    color = androidx.compose.ui.graphics.Color.White
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Text(
                    text = "Entre ton adresse email pour faire une demande de réinitialisation de mot de passe.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.9f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(30.dp))
                
                OutlinedTextField(
                    value = email,
                    onValueChange = viewModel::updateEmail,
                    label = { Text("Email") },
                    placeholder = { Text("ton@email.com") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                
                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Button(
                    onClick = { viewModel.sendResetEmail() },
                    enabled = isValid && !isLoading,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isValid && !isLoading) AppGold else androidx.compose.ui.graphics.Color.Gray
                    )
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = androidx.compose.ui.graphics.Color.Black
                        )
                    } else {
                        Text("Envoyer", color = androidx.compose.ui.graphics.Color.Black)
                    }
                }
            } else {
                // Success message
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    tint = androidx.compose.ui.graphics.Color.Green
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Text(
                    text = "Demande prise en compte",
                    style = MaterialTheme.typography.headlineMedium,
                    color = androidx.compose.ui.graphics.Color.White
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Text(
                    text = "Ta demande a été prise en compte. Nous allons t'envoyer un lien pour réinitialiser ton mot de passe.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.9f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Button(
                    onClick = onNavigateBack,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AppGold)
                ) {
                    Text("Retour à la connexion", color = androidx.compose.ui.graphics.Color.Black)
                }
            }
        }
    }
}
