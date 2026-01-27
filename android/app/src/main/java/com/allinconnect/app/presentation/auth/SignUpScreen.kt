package com.allinconnect.app.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.allinconnect.app.presentation.components.FooterBar
import com.allinconnect.app.presentation.theme.AppGradient
import com.allinconnect.app.presentation.theme.AppRed

@Composable
fun SignUpScreen(
    onNavigateBack: () -> Unit,
    onNavigateToSubscription: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val firstName by viewModel.firstName.collectAsStateWithLifecycle()
    val lastName by viewModel.lastName.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val postalCode by viewModel.postalCode.collectAsStateWithLifecycle()
    val birthDay by viewModel.birthDay.collectAsStateWithLifecycle()
    val birthMonth by viewModel.birthMonth.collectAsStateWithLifecycle()
    val birthYear by viewModel.birthYear.collectAsStateWithLifecycle()
    val referralCode by viewModel.referralCode.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val signUpSuccess by viewModel.signUpSuccess.collectAsStateWithLifecycle()
    val isValid = viewModel.isValid
    
    var showPassword by remember { mutableStateOf(false) }
    
    LaunchedEffect(signUpSuccess) {
        if (signUpSuccess) {
            onNavigateToSubscription()
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
                .padding(bottom = 80.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            
            // Back button
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }
            
            Text(
                text = "Inscription",
                style = MaterialTheme.typography.headlineLarge,
                color = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            )
            
            // Form fields
            OutlinedTextField(
                value = lastName,
                onValueChange = { viewModel.updateLastName(it) },
                label = { Text("Nom") },
                placeholder = { Text("Ton nom") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )
            
            OutlinedTextField(
                value = firstName,
                onValueChange = { viewModel.updateFirstName(it) },
                label = { Text("Prénom") },
                placeholder = { Text("Ton prénom") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )
            
            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                label = { Text("Email") },
                placeholder = { Text("ton@email.com") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )
            
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                label = { Text("Mot de passe") },
                placeholder = { Text("Ton mot de passe") },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (showPassword) "Hide password" else "Show password"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )
            
            OutlinedTextField(
                value = postalCode,
                onValueChange = { viewModel.updatePostalCode(it) },
                label = { Text("Code postal") },
                placeholder = { Text("69001") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )
            
            // Birth date fields
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = birthDay,
                    onValueChange = { viewModel.updateBirthDay(it) },
                    label = { Text("Jour") },
                    placeholder = { Text("JJ") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                
                OutlinedTextField(
                    value = birthMonth,
                    onValueChange = { viewModel.updateBirthMonth(it) },
                    label = { Text("Mois") },
                    placeholder = { Text("MM") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                
                OutlinedTextField(
                    value = birthYear,
                    onValueChange = { viewModel.updateBirthYear(it) },
                    label = { Text("Année") },
                    placeholder = { Text("AAAA") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1.5f)
                )
            }
            
            OutlinedTextField(
                value = referralCode,
                onValueChange = { viewModel.updateReferralCode(it) },
                label = { Text("Parrainage (optionnel)") },
                placeholder = { Text("Code de parrainage") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )
            
            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                )
            }
            
            Button(
                onClick = { viewModel.signUp { } },
                enabled = isValid && !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isValid && !isLoading) AppRed else androidx.compose.ui.graphics.Color.Gray
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = androidx.compose.ui.graphics.Color.White
                    )
                } else {
                    Text("S'inscrire")
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

