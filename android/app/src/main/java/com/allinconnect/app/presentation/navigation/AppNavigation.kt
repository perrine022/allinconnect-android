package com.allinconnect.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allinconnect.app.presentation.auth.LoginScreen
import com.allinconnect.app.presentation.auth.WelcomeScreen
import com.allinconnect.app.presentation.home.HomeScreen
import com.allinconnect.app.presentation.onboarding.TutorialScreen
import com.allinconnect.app.presentation.tabs.TabBarScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    // TODO: Check if user has seen tutorial and is logged in
    val startDestination = Screen.Tutorial.route
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Tutorial.route) {
            TutorialScreen(
                onComplete = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Tutorial.route) { inclusive = true }
                    }
                },
                onSkip = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Tutorial.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onNavigateToSignUp = {
                    // TODO: Navigate to signup
                }
            )
        }
        
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.TabBar.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    // TODO: Navigate to signup
                },
                onNavigateToForgotPassword = {
                    // TODO: Navigate to forgot password
                }
            )
        }
        
        composable(Screen.TabBar.route) {
            TabBarScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object Tutorial : Screen("tutorial")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object TabBar : Screen("tabbar")
}

