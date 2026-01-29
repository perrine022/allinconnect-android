package com.allinconnect.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allinconnect.app.presentation.auth.*
import com.allinconnect.app.presentation.billing.*
import com.allinconnect.app.presentation.card.*
import com.allinconnect.app.presentation.home.HomeScreen
import com.allinconnect.app.presentation.offer.OfferDetailScreen
import com.allinconnect.app.presentation.offers.OffersScreen
import com.allinconnect.app.presentation.onboarding.TutorialScreen
import com.allinconnect.app.presentation.partner.PartnerDetailScreen
import com.allinconnect.app.presentation.partners.PartnersListScreen
import com.allinconnect.app.presentation.profile.*
import com.allinconnect.app.presentation.pro.*
import com.allinconnect.app.presentation.splash.SplashScreen
import com.allinconnect.app.presentation.tabs.TabBarScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Tutorial : Screen("tutorial")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object ForgotPassword : Screen("forgot_password")
    object ClientSubscription : Screen("client_subscription")
    object ProSubscription : Screen("pro_subscription")
    object TabBar : Screen("tabbar")
    
    // Home & Offers
    object Home : Screen("home")
    object Offers : Screen("offers")
    object OfferDetail : Screen("offer_detail/{offerId}") {
        fun createRoute(offerId: Int) = "offer_detail/$offerId"
    }
    
    // Partners
    object Partners : Screen("partners")
    object PartnerDetail : Screen("partner_detail/{partnerId}") {
        fun createRoute(partnerId: Int) = "partner_detail/$partnerId"
    }
    
    // Card
    object Card : Screen("card")
    object DigitalCardInfo : Screen("digital_card_info")
    object FamilyCardManagement : Screen("family_card_management")
    object SavingsList : Screen("savings_list")
    object StripePaymentSheet : Screen("stripe_payment_sheet/{clientSecret}") {
        fun createRoute(clientSecret: String?) = "stripe_payment_sheet/${clientSecret ?: ""}"
    }
    object PaymentResult : Screen("payment_result") {
        fun createRoute(success: Boolean, message: String? = null) = 
            "payment_result?success=$success&message=${message ?: ""}"
    }
    
    // Profile
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
    object Settings : Screen("settings")
    object NotificationPreferences : Screen("notification_preferences")
    object HelpSupport : Screen("help_support")
    object Terms : Screen("terms")
    object ChangePassword : Screen("change_password")
    object Referrals : Screen("referrals")
    
    // Pro
    object ProOffers : Screen("pro_offers")
    object CreateOffer : Screen("create_offer")
    object ManageEstablishment : Screen("manage_establishment")
    object PaymentHistory : Screen("payment_history")
    object Invoices : Screen("invoices")
    object ManageSubscriptions : Screen("manage_subscriptions")
    object ProInfo : Screen("pro_info")
    
    // Billing
    object Subscribe : Screen("subscribe")
    object ManageSubscription : Screen("manage_subscription")
    object StripeSubscriptionPaymentSheet : Screen("stripe_subscription_payment_sheet/{clientSecret}/{customerId}/{ephemeralKeySecret}/{publishableKey}") {
        fun createRoute(
            clientSecret: String?,
            customerId: String?,
            ephemeralKeySecret: String?,
            publishableKey: String?
        ) = "stripe_subscription_payment_sheet/${clientSecret ?: ""}/${customerId ?: ""}/${ephemeralKeySecret ?: ""}/${publishableKey ?: ""}"
    }
}

// Helper function to create NamedNavArgument
// In Navigation Compose, NamedNavArgument is a typealias for Pair<String, NavArgument>
private fun navArgument(
    name: String,
    type: NavType<*>,
    nullable: Boolean = false,
    defaultValue: Any? = null
): NamedNavArgument {
    val builder = NavArgument.Builder()
        .setType(type)
    if (nullable) {
        builder.setIsNullable(true)
    }
    if (defaultValue != null) {
        builder.setDefaultValue(defaultValue)
    }
    val navArg = builder.build()
    // NamedNavArgument is a typealias for Pair<String, NavArgument> in Navigation Compose
    @Suppress("UNCHECKED_CAST")
    return Pair(name, navArg) as NamedNavArgument
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToTutorial = { navController.navigate(Screen.Tutorial.route) },
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateToTabBar = { navController.navigate(Screen.TabBar.route) }
            )
        }
        
        composable(Screen.Tutorial.route) {
            TutorialScreen(
                onComplete = { navController.navigate(Screen.Welcome.route) }
            )
        }
        
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) }
            )
        }
        
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToTabBar = { navController.navigate(Screen.TabBar.route) },
                onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) },
                onNavigateToForgotPassword = { navController.navigate(Screen.ForgotPassword.route) }
            )
        }
        
        composable(Screen.SignUp.route) {
            SignUpScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToSubscription = { navController.navigate(Screen.ClientSubscription.route) }
            )
        }
        
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.ClientSubscription.route) {
            ClientSubscriptionScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToTabBar = { navController.navigate(Screen.TabBar.route) },
                onNavigateToPaymentSheet = { clientSecret, customerId, ephemeralKey, publishableKey ->
                    navController.navigate(
                        Screen.StripeSubscriptionPaymentSheet.createRoute(
                            clientSecret,
                            customerId,
                            ephemeralKey,
                            publishableKey
                        )
                    )
                }
            )
        }
        
        composable(Screen.ProSubscription.route) {
            ProSubscriptionScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToTabBar = { navController.navigate(Screen.TabBar.route) },
                onNavigateToPaymentSheet = { clientSecret, customerId, ephemeralKey, publishableKey ->
                    navController.navigate(
                        Screen.StripeSubscriptionPaymentSheet.createRoute(
                            clientSecret,
                            customerId,
                            ephemeralKey,
                            publishableKey
                        )
                    )
                }
            )
        }
        
        composable(Screen.TabBar.route) {
            TabBarScreen(
                onNavigateToOfferDetail = { offerId ->
                    navController.navigate(Screen.OfferDetail.createRoute(offerId))
                },
                onNavigateToPartnerDetail = { partnerId ->
                    navController.navigate(Screen.PartnerDetail.createRoute(partnerId))
                },
                onNavigateToEditProfile = {
                    navController.navigate(Screen.EditProfile.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                },
                onNavigateToNotificationPreferences = {
                    navController.navigate(Screen.NotificationPreferences.route)
                },
                onNavigateToHelpSupport = {
                    navController.navigate(Screen.HelpSupport.route)
                },
                onNavigateToTerms = {
                    navController.navigate(Screen.Terms.route)
                },
                onNavigateToChangePassword = {
                    navController.navigate(Screen.ChangePassword.route)
                },
                onNavigateToReferrals = {
                    navController.navigate(Screen.Referrals.route)
                },
                onNavigateToDigitalCardInfo = {
                    navController.navigate(Screen.DigitalCardInfo.route)
                },
                onNavigateToFamilyCardManagement = {
                    navController.navigate(Screen.FamilyCardManagement.route)
                },
                onNavigateToSavingsList = {
                    navController.navigate(Screen.SavingsList.route)
                }
            )
        }
        
        composable(
            route = Screen.OfferDetail.route,
            arguments = listOf(
                navArgument("offerId", NavType.IntType)
            )
        ) { backStackEntry ->
            val offerId = backStackEntry.arguments?.getInt("offerId") ?: 0
            OfferDetailScreen(
                offerId = offerId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.PartnerDetail.route,
            arguments = listOf(
                navArgument("partnerId", NavType.IntType)
            )
        ) { backStackEntry ->
            val partnerId = backStackEntry.arguments?.getInt("partnerId") ?: 0
            PartnerDetailScreen(
                partnerId = partnerId,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToOfferDetail = { offerId ->
                    navController.navigate(Screen.OfferDetail.createRoute(offerId))
                }
            )
        }
        
        composable(Screen.Partners.route) {
            PartnersListScreen(
                onNavigateToPartnerDetail = { partnerId ->
                    navController.navigate(Screen.PartnerDetail.createRoute(partnerId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.DigitalCardInfo.route) {
            DigitalCardInfoScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.FamilyCardManagement.route) {
            FamilyCardManagementScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.SavingsList.route) {
            SavingsListScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.EditProfile.route) {
            EditProfileScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.NotificationPreferences.route) {
            NotificationPreferencesScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.HelpSupport.route) {
            HelpSupportScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Terms.route) {
            TermsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.ChangePassword.route) {
            ChangePasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Referrals.route) {
            ReferralsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.ProOffers.route) {
            ProOffersScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCreateOffer = { navController.navigate(Screen.CreateOffer.route) }
            )
        }
        
        composable(Screen.CreateOffer.route) {
            CreateOfferScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.ManageEstablishment.route) {
            ManageEstablishmentScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.PaymentHistory.route) {
            PaymentHistoryScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Invoices.route) {
            InvoicesScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.ProInfo.route) {
            ProInfoScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Subscribe.route) {
            SubscribeScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPaymentSheet = { clientSecret, customerId, ephemeralKey, publishableKey ->
                    navController.navigate(
                        Screen.StripeSubscriptionPaymentSheet.createRoute(
                            clientSecret,
                            customerId,
                            ephemeralKey,
                            publishableKey
                        )
                    )
                }
            )
        }
        
        composable(Screen.ManageSubscription.route) {
            ManageSubscriptionScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.StripePaymentSheet.route,
            arguments = listOf(
                navArgument("clientSecret", NavType.StringType, nullable = true)
            )
        ) { backStackEntry ->
            val clientSecret = backStackEntry.arguments?.getString("clientSecret")
            StripePaymentSheetScreen(
                clientSecret = clientSecret,
                onNavigateBack = { navController.popBackStack() },
                onPaymentResult = { success, message ->
                    navController.navigate(
                        Screen.PaymentResult.route + "?success=$success&message=${message ?: ""}"
                    )
                }
            )
        }
        
        composable(
            route = Screen.PaymentResult.route + "?success={success}&message={message}",
            arguments = listOf(
                navArgument("success", NavType.BoolType, defaultValue = false),
                navArgument("message", NavType.StringType, nullable = true, defaultValue = null)
            )
        ) { backStackEntry ->
            val success = backStackEntry.arguments?.getBoolean("success") ?: false
            val message = backStackEntry.arguments?.getString("message")
            PaymentResultScreen(
                isSuccess = success,
                message = message,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.ManageSubscriptions.route) {
            ManageSubscriptionsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.StripeSubscriptionPaymentSheet.route,
            arguments = listOf(
                navArgument("clientSecret", NavType.StringType, nullable = true),
                navArgument("customerId", NavType.StringType, nullable = true),
                navArgument("ephemeralKeySecret", NavType.StringType, nullable = true),
                navArgument("publishableKey", NavType.StringType, nullable = true)
            )
        ) { backStackEntry ->
            val clientSecret = backStackEntry.arguments?.getString("clientSecret")
            val customerId = backStackEntry.arguments?.getString("customerId")
            val ephemeralKeySecret = backStackEntry.arguments?.getString("ephemeralKeySecret")
            val publishableKey = backStackEntry.arguments?.getString("publishableKey")
            StripeSubscriptionPaymentSheetScreen(
                clientSecret = clientSecret,
                customerId = customerId,
                ephemeralKeySecret = ephemeralKeySecret,
                publishableKey = publishableKey,
                onNavigateBack = { navController.popBackStack() },
                onPaymentResult = { success, message ->
                    navController.navigate(
                        Screen.PaymentResult.route + "?success=$success&message=${message ?: ""}"
                    )
                }
            )
        }
    }
}
