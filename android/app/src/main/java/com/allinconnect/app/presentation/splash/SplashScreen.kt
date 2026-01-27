package com.allinconnect.app.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToTutorial: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToTabBar: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ALL IN Connect",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
    
    LaunchedEffect(Unit) {
        delay(2000)
        
        val showTutorial = viewModel.shouldShowTutorial()
        val loggedIn = viewModel.isLoggedIn()
        
        when {
            showTutorial -> onNavigateToTutorial()
            loggedIn -> onNavigateToTabBar()
            else -> onNavigateToLogin()
        }
    }
}
