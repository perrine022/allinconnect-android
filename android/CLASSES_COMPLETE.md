# Liste complète des classes portées iOS → Android

## ✅ COMPLÈTEMENT PORTÉES

### 1. Services API → APIs + Repositories (12/12)

| iOS Service | Android API | Android Repository | Status |
|------------|-------------|-------------------|--------|
| AuthAPIService | AuthApi | AuthRepository | ✅ |
| OffersAPIService | OffersApi | OffersRepository | ✅ |
| PartnersAPIService | PartnersApi | PartnersRepository | ✅ |
| ProfileAPIService | ProfileApi | ProfileRepository | ✅ |
| BillingAPIService | BillingApi | BillingRepository | ✅ |
| SubscriptionsAPIService | SubscriptionsApi | SubscriptionsRepository | ✅ |
| WalletAPIService | WalletApi | WalletRepository | ✅ |
| SavingsAPIService | SavingsApi | SavingsRepository | ✅ |
| FavoritesAPIService | FavoritesApi | FavoritesRepository | ✅ |
| RatingsAPIService | RatingsApi | RatingsRepository | ✅ |
| NotificationPreferencesAPIService | NotificationPreferencesApi | NotificationPreferencesRepository | ✅ |
| InvoicesAPIService | InvoicesApi | InvoicesRepository | ✅ |
| PaymentAPIService | PaymentApi | PaymentRepository | ✅ |

### 2. Modèles Domain (6/6)

| iOS Model | Android Domain Model | Status |
|-----------|---------------------|--------|
| User | User | ✅ |
| Offer | Offer | ✅ |
| Partner | Partner | ✅ |
| Professional | Professional | ✅ |
| Review | Review | ✅ |
| SavingsEntry | SavingsEntry | ✅ |

### 3. DTOs (tous créés)

#### Auth
- ✅ LoginRequest
- ✅ RegistrationRequest
- ✅ AuthResponse
- ✅ ForgotPasswordRequest
- ✅ ResetPasswordRequest

#### Offer
- ✅ OfferResponse
- ✅ ProfessionalResponse
- ✅ OfferCategory (enum)
- ✅ OfferType (enum)

#### Partner
- ✅ PartnerProfessionalResponse
- ✅ SubscriptionPlanResponse

#### Profile
- ✅ UpdateProfileRequest
- ✅ ChangePasswordRequest
- ✅ CardMember
- ✅ CardResponse
- ✅ UserMeResponse
- ✅ UserLightResponse
- ✅ PaymentResponse

#### Billing
- ✅ SubscriptionPaymentSheetResponse
- ✅ SubscriptionStatusResponse
- ✅ PortalResponse
- ✅ CancelSubscriptionResponse

#### Subscription
- ✅ SubscriptionPlanResponse
- ✅ CardMembersResponse
- ✅ CardOwnerResponse
- ✅ FamilyCardEmailsResponse
- ✅ UpdateFamilyCardEmailsRequest
- ✅ PaymentIntentResponse
- ✅ InviteRequest
- ✅ RemoveMemberRequest

#### Wallet
- ✅ WalletHistoryResponse
- ✅ WalletUserResponse
- ✅ WalletRequestResponse
- ✅ WalletRequest

#### Savings
- ✅ SavingsResponse
- ✅ SavingsRequest

#### Rating
- ✅ RatingRequest
- ✅ RaterResponse
- ✅ RatingResponse

#### Notification
- ✅ NotificationPreferencesResponse
- ✅ NotificationPreferencesRequest

#### Invoice
- ✅ InvoiceResponse

#### Payment
- ✅ PaymentSheetRequest
- ✅ PaymentSheetResponse
- ✅ CreatePaymentIntentRequest
- ✅ CreatePaymentIntentResponse
- ✅ CreateCustomerResponse
- ✅ PaymentStatusResponse

### 4. Core Services

| iOS | Android | Status |
|-----|---------|--------|
| APIService | NetworkModule (Retrofit + OkHttp) | ✅ |
| AuthTokenManager | AuthTokenManager (DataStore) | ✅ |
| APIConfig | ApiConfig | ✅ |
| APIError | ApiError (sealed class) | ✅ |

### 5. Écrans de base

| iOS View | Android Screen | Status |
|----------|---------------|--------|
| TutorialView | TutorialScreen | ✅ (squelette) |
| WelcomeView | WelcomeScreen | ✅ (squelette) |
| LoginView | LoginScreen | ✅ (complet) |
| TabBarView | TabBarScreen | ✅ (complet) |
| HomeView | HomeScreen | ✅ (squelette) |
| OffersView | OffersScreen | ✅ (squelette) |
| CardView | CardScreen | ✅ (squelette) |
| ProfileView | ProfileScreen | ✅ (squelette) |

### 6. ViewModels

| iOS ViewModel | Android ViewModel | Status |
|---------------|-------------------|--------|
| LoginViewModel | LoginViewModel | ✅ |

## 🚧 À COMPLÉTER

### ViewModels manquants (16)
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
- [ ] ProSubscriptionViewModel
- [ ] ClientSubscriptionViewModel
- [ ] OnboardingViewModel
- [ ] TutorialViewModel

### Écrans manquants (~30)
- [ ] SignUpView → SignUpScreen
- [ ] ForgotPasswordView → ForgotPasswordScreen
- [ ] OfferDetailView → OfferDetailScreen
- [ ] PartnersListView → PartnersListScreen
- [ ] PartnerDetailView → PartnerDetailScreen
- [ ] SettingsView → SettingsScreen
- [ ] EditProfileView → EditProfileScreen
- [ ] ChangePasswordView → ChangePasswordScreen
- [ ] NotificationPreferencesView → NotificationPreferencesScreen
- [ ] HelpSupportView → HelpSupportScreen
- [ ] TermsView → TermsScreen
- [ ] FamilyCardEmailsView → FamilyCardEmailsScreen
- [ ] CardSubscriptionView → CardSubscriptionScreen
- [ ] SavingsListView → SavingsListScreen
- [ ] FamilyCardManagementView → FamilyCardManagementScreen
- [ ] DigitalCardInfoView → DigitalCardInfoScreen
- [ ] StripePaymentSheetView → StripePaymentSheetScreen
- [ ] PaymentResultView → PaymentResultScreen
- [ ] ProInfoView → ProInfoScreen
- [ ] ProOffersView → ProOffersScreen
- [ ] CreateOfferView → CreateOfferScreen
- [ ] ManageEstablishmentView → ManageEstablishmentScreen
- [ ] ManageSubscriptionsView → ManageSubscriptionsScreen
- [ ] InvoicesView → InvoicesScreen
- [ ] PaymentHistoryView → PaymentHistoryScreen
- [ ] WalletView → WalletScreen
- [ ] SubscribeView → SubscribeScreen
- [ ] ManageSubscriptionView → ManageSubscriptionScreen
- [ ] StripeSubscriptionPaymentSheetView → StripeSubscriptionPaymentSheetScreen
- [ ] ClientSubscriptionView → ClientSubscriptionScreen
- [ ] ProSubscriptionView → ProSubscriptionScreen
- [ ] OnboardingView → OnboardingScreen

### Composants UI réutilisables (~25)
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
- [ ] OfferListCardSkeleton
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

### Mappers manquants (5)
- [ ] PartnerMapper (PartnerProfessionalResponse → Partner)
- [ ] ProfessionalMapper (PartnerProfessionalResponse → Professional)
- [ ] ReviewMapper (RatingResponse → Review)
- [ ] SavingsMapper (SavingsResponse → SavingsEntry)
- [ ] ProfileMapper (UserMeResponse → User)

### Utils manquants
- [ ] ImageURLHelper
- [ ] DefaultImageHelper
- [ ] LocationService
- [ ] PushManager
- [ ] CacheService
- [ ] PaymentStatusManager

## 📊 Statistiques

- **Services API** : 13/13 portés (100%)
- **Modèles Domain** : 6/6 portés (100%)
- **DTOs** : ~50+ créés (100%)
- **Repositories** : 13/13 créés (100%)
- **APIs Retrofit** : 13/13 créées (100%)
- **ViewModels** : 1/20 créés (5%)
- **Écrans** : 8/40 créés (20%)
- **Composants UI** : 0/28 créés (0%)

## 🎯 Prochaines étapes prioritaires

1. Créer les mappers manquants
2. Implémenter les ViewModels principaux (Home, Offers, Partners)
3. Créer les composants UI réutilisables
4. Compléter les écrans principaux
5. Implémenter les features Pro
6. Intégrer Stripe Payment Sheet
7. Ajouter les notifications push

---

**Dernière mise à jour** : Janvier 2026

