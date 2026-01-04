# Fichiers Swift supprimés (portés vers Android)

## ✅ Services API supprimés (13 fichiers)

1. ✅ `Core/Services/AuthAPIService.swift` → `data/api/AuthApi.kt` + `data/repository/AuthRepository.kt`
2. ✅ `Core/Services/OffersAPIService.swift` → `data/api/OffersApi.kt` + `data/repository/OffersRepository.kt`
3. ✅ `Core/Services/PartnersAPIService.swift` → `data/api/PartnersApi.kt` + `data/repository/PartnersRepository.kt`
4. ✅ `Core/Services/ProfileAPIService.swift` → `data/api/ProfileApi.kt` + `data/repository/ProfileRepository.kt`
5. ✅ `Core/Services/BillingAPIService.swift` → `data/api/BillingApi.kt` + `data/repository/BillingRepository.kt`
6. ✅ `Core/Services/SubscriptionsAPIService.swift` → `data/api/SubscriptionsApi.kt` + `data/repository/SubscriptionsRepository.kt`
7. ✅ `Core/Services/WalletAPIService.swift` → `data/api/WalletApi.kt` + `data/repository/WalletRepository.kt`
8. ✅ `Core/Services/SavingsAPIService.swift` → `data/api/SavingsApi.kt` + `data/repository/SavingsRepository.kt`
9. ✅ `Core/Services/FavoritesAPIService.swift` → `data/api/FavoritesApi.kt` + `data/repository/FavoritesRepository.kt`
10. ✅ `Core/Services/RatingsAPIService.swift` → `data/api/RatingsApi.kt` + `data/repository/RatingsRepository.kt`
11. ✅ `Core/Services/NotificationPreferencesAPIService.swift` → `data/api/NotificationPreferencesApi.kt` + `data/repository/NotificationPreferencesRepository.kt`
12. ✅ `Core/Services/InvoicesAPIService.swift` → `data/api/InvoicesApi.kt` + `data/repository/InvoicesRepository.kt`
13. ✅ `Core/Services/PaymentAPIService.swift` → `data/api/PaymentApi.kt` + `data/repository/PaymentRepository.kt`
14. ✅ `Core/Services/APIService.swift` → `core/network/NetworkModule.kt`

## ✅ Modèles supprimés (6 fichiers)

1. ✅ `Core/Models/User.swift` → `domain/model/User.kt`
2. ✅ `Core/Models/Offer.swift` → `domain/model/Offer.kt`
3. ✅ `Core/Models/Partner.swift` → `domain/model/Partner.kt`
4. ✅ `Core/Models/Professional.swift` → `domain/model/Professional.kt`
5. ✅ `Core/Models/Review.swift` → `domain/model/Review.kt`
6. ✅ `Core/Models/SavingsEntry.swift` → `domain/model/SavingsEntry.kt`

## ✅ Core Services supprimés (7 fichiers)

1. ✅ `Core/AppState.swift` → `core/state/AppState.kt`
2. ✅ `Core/Utils/ImageURLHelper.swift` → `core/utils/ImageURLHelper.kt`
3. ✅ `Core/Utils/DefaultImageHelper.swift` → `core/utils/DefaultImageHelper.kt`
4. ✅ `Core/Services/LocationService.swift` → `core/location/LocationService.kt`
5. ✅ `Core/Services/PushManager.swift` → `core/notifications/PushManager.kt`
6. ✅ `Core/Services/PaymentStatusManager.swift` → `core/payment/PaymentStatusManager.kt`
7. ✅ `Core/Services/CacheService.swift` → `core/cache/CacheService.kt`

## ✅ Thème supprimé (1 fichier)

1. ✅ `Core/Theme/AppColors.swift` → `res/values/colors.xml` + `presentation/theme/Theme.kt`

## ✅ Mock Data supprimé (1 fichier)

1. ✅ `Core/Services/MockDataService.swift` → Non nécessaire (on utilise directement les APIs)

## 📊 Total

- **28 fichiers Swift supprimés**
- **Toutes les classes de services, modèles, thème portées vers Android**

## 📝 Fichiers conservés (spécifiques iOS/UI)

Les fichiers suivants sont **intentionnellement conservés** car ils sont spécifiques à l'UI iOS :
- Tous les ViewModels (à réimplémenter en Android avec Compose)
- Toutes les Views (à réimplémenter en Android avec Compose)
- Tous les Components (à réimplémenter en Android avec Compose)
- AppDelegate.swift (spécifique iOS)
- MockDataService.swift (peut être supprimé si non utilisé)

---

**Date** : Janvier 2026

