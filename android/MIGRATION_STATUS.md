# État de la Migration iOS → Android

## ✅ Complété

### 1. Structure de base du projet
- ✅ Configuration Gradle (build.gradle.kts, settings.gradle.kts)
- ✅ AndroidManifest.xml avec permissions
- ✅ Application class avec Hilt
- ✅ Configuration des ressources (strings, colors, themes)
- ✅ README Android complet

### 2. Couche Data
- ✅ ApiConfig avec gestion des environnements (dev/prod)
- ✅ ApiError avec gestion d'erreurs complète
- ✅ NetworkModule (Retrofit + OkHttp + interceptors)
- ✅ AuthTokenManager avec DataStore (stockage sécurisé)
- ✅ DTOs : AuthDto, OfferDto
- ✅ Interfaces API : AuthApi, OffersApi
- ✅ Mappers : OfferMapper (DTO → Domain)
- ✅ Repositories : AuthRepository, OffersRepository

### 3. Couche Domain
- ✅ Modèles : User, Offer, Partner
- ✅ Enums : UserType, OfferType

### 4. Couche Presentation
- ✅ MainActivity avec Splash Screen
- ✅ Navigation Compose (AppNavigation)
- ✅ Thème Material 3 (AllInConnectTheme)
- ✅ Écrans de base :
  - TutorialScreen
  - WelcomeScreen
  - LoginScreen + LoginViewModel
  - TabBarScreen avec 4 onglets
  - HomeScreen, OffersScreen, CardScreen, ProfileScreen (squelettes)

### 5. Authentification
- ✅ LoginViewModel avec validation
- ✅ AuthRepository avec gestion des tokens
- ✅ Navigation après login

## 🚧 À compléter (priorité haute)

### 1. DTOs manquants
- [ ] PartnerDto
- [ ] ProfessionalDto
- [ ] ReviewDto
- [ ] SavingsEntryDto
- [ ] SubscriptionDto
- [ ] BillingDto
- [ ] ProfileDto
- [ ] WalletDto
- [ ] NotificationPreferencesDto

### 2. APIs manquantes
- [ ] PartnersApi
- [ ] ProfileApi
- [ ] BillingApi
- [ ] SubscriptionsApi
- [ ] PaymentApi
- [ ] WalletApi
- [ ] SavingsApi
- [ ] FavoritesApi
- [ ] RatingsApi
- [ ] NotificationPreferencesApi
- [ ] InvoicesApi

### 3. Repositories manquants
- [ ] PartnersRepository
- [ ] ProfileRepository
- [ ] BillingRepository
- [ ] SubscriptionsRepository
- [ ] PaymentRepository
- [ ] WalletRepository
- [ ] SavingsRepository
- [ ] FavoritesRepository
- [ ] RatingsRepository

### 4. Écrans à implémenter
- [ ] SignUpScreen + ViewModel
- [ ] ForgotPasswordScreen
- [ ] HomeScreen (avec liste d'offres, recherche, filtres)
- [ ] OffersScreen (liste complète avec filtres)
- [ ] OfferDetailScreen
- [ ] PartnersListScreen
- [ ] PartnerDetailScreen
- [ ] CardScreen (carte digitale, statistiques)
- [ ] CardSubscriptionScreen
- [ ] SavingsListScreen
- [ ] FamilyCardManagementScreen
- [ ] ProfileScreen (complet)
- [ ] EditProfileScreen
- [ ] SettingsScreen
- [ ] ChangePasswordScreen
- [ ] NotificationPreferencesScreen
- [ ] HelpSupportScreen
- [ ] TermsScreen
- [ ] ProInfoScreen
- [ ] ProOffersScreen
- [ ] CreateOfferScreen
- [ ] ManageEstablishmentScreen
- [ ] ManageSubscriptionsScreen
- [ ] InvoicesScreen
- [ ] PaymentHistoryScreen
- [ ] WalletScreen
- [ ] SubscribeScreen
- [ ] StripePaymentSheetScreen

### 5. ViewModels manquants
- [ ] SignUpViewModel
- [ ] HomeViewModel
- [ ] OffersViewModel
- [ ] OfferDetailViewModel
- [ ] PartnersListViewModel
- [ ] PartnerDetailViewModel
- [ ] CardViewModel
- [ ] ProfileViewModel
- [ ] EditProfileViewModel
- [ ] ChangePasswordViewModel
- [ ] NotificationPreferencesViewModel
- [ ] ProOffersViewModel
- [ ] CreateOfferViewModel
- [ ] ManageSubscriptionsViewModel
- [ ] InvoicesViewModel
- [ ] WalletViewModel
- [ ] BillingViewModel

### 6. Composants UI réutilisables
- [ ] ActionButton
- [ ] BadgeView
- [ ] Club10Card
- [ ] ContactRow
- [ ] CurrentOfferCard
- [ ] CustomSectorPicker
- [ ] DateRangePicker
- [ ] FilterButton
- [ ] FilterSheet
- [ ] FooterBar
- [ ] InfoSection
- [ ] LocationPermissionView
- [ ] NavigationButton
- [ ] OfferCard
- [ ] OfferImage
- [ ] OfferListCard
- [ ] PartnerCard
- [ ] ProCard
- [ ] ProfessionalCard
- [ ] ProfileHeaderView
- [ ] ProfileMenuRow
- [ ] RatingPopupView
- [ ] ReviewCard
- [ ] SearchBar
- [ ] StarRatingView
- [ ] StatCard
- [ ] WebView

### 7. Features avancées
- [ ] Gestion des images (Coil avec cache)
- [ ] LocationService (permissions, géolocalisation)
- [ ] Push Notifications (Firebase Messaging)
- [ ] Stripe Payment Sheet intégration
- [ ] Gestion offline (cache Room si nécessaire)
- [ ] Deep linking (allinconnect://)

### 8. Configuration finale
- [ ] App icons (mipmap)
- [ ] Splash screen assets
- [ ] Firebase google-services.json
- [ ] Configuration des environnements (local.properties)
- [ ] Tests unitaires de base
- [ ] Tests UI basiques

## 📋 Endpoints API identifiés

### Auth
- POST /auth/register
- POST /auth/authenticate
- POST /auth/forgot-password
- POST /auth/reset-password

### Offers
- GET /offers
- GET /offers/active
- GET /offers/{id}
- GET /offers/my-offers
- GET /offers/professional/{professionalId}
- GET /offers/professional/{professionalId}/active
- POST /offers
- PUT /offers/{id}
- POST /offers/{id}/archive
- DELETE /offers/{id}

### Partners
- GET /users/professionals
- GET /users/professionals/by-city
- GET /users/professionals/search
- GET /users/{id}

### Profile
- GET /users/me
- GET /users/me/light
- PUT /users/profile
- POST /users/change-password

### Billing
- POST /billing/subscription/payment-sheet
- GET /billing/subscription/status
- POST /billing/portal
- POST /billing/subscription/cancel

### Subscriptions
- GET /subscriptions/plans
- POST /subscriptions/subscribe/{planId}
- GET /subscriptions/my-payments
- POST /subscriptions/create-payment-intent

### Cards
- POST /cards/invite
- POST /cards/remove-member
- GET /cards/members
- GET /cards/owner
- GET /cards/family/emails
- PUT /cards/family/emails

### Wallet
- GET /wallet/history
- POST /wallet/request
- GET /wallet/requests

### Savings
- GET /savings
- POST /savings
- PUT /savings/{id}
- DELETE /savings/{id}

### Favorites
- GET /users/favorites
- POST /users/favorites/{professionalId}
- DELETE /users/favorites/{professionalId}

### Ratings
- POST /ratings
- GET /ratings/user/{userId}
- GET /ratings/user/{userId}/average

### Notifications
- GET /notification-preferences
- PUT /notification-preferences

### Invoices
- GET /billing/invoices

### Payment
- GET /payment/public-key
- POST /payment/payment-sheet
- POST /payment/create-payment-intent
- POST /payment/create-customer
- GET /payment/status/{paymentIntentId}

## 🔧 Configuration requise

1. **local.properties** (à créer à la racine du projet Android) :
```properties
API_BASE_URL=http://127.0.0.1:8080/api/v1
API_BASE_URL_PRODUCTION=https://allinconnect-back-1.onrender.com/api/v1
```

2. **google-services.json** (Firebase) :
   - Télécharger depuis Firebase Console
   - Placer dans `app/`

3. **Build** :
```bash
cd android
./gradlew assembleDebug
```

## 📝 Notes importantes

- Le projet utilise **Clean Architecture** avec séparation claire des couches
- **Hilt** pour l'injection de dépendances
- **Jetpack Compose** pour toute l'UI
- **Kotlinx Serialization** pour la sérialisation JSON
- **DataStore** pour le stockage sécurisé des tokens (remplace UserDefaults iOS)
- Les couleurs et thèmes correspondent à l'app iOS
- La navigation suit le même flow que iOS (Tutorial → Welcome → Login → TabBar)

## 🎯 Prochaines étapes recommandées

1. Compléter les DTOs et APIs manquants
2. Implémenter les écrans principaux (Home, Offers, Partners)
3. Ajouter les composants UI réutilisables
4. Implémenter les features Pro
5. Intégrer Stripe Payment Sheet
6. Ajouter les notifications push
7. Finaliser le theming et les assets

---

**Dernière mise à jour** : Janvier 2026

