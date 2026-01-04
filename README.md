# All In Connect - Android

Application Android native développée en Kotlin avec Jetpack Compose pour connecter les utilisateurs avec des professionnels locaux et bénéficier d'avantages exclusifs via le CLUB10.

**Développé par** : Perrine Honoré

## Architecture

Architecture **Clean Architecture** avec séparation en 3 couches :

- **Presentation** : Jetpack Compose UI, ViewModels, Navigation
- **Domain** : Modèles de domaine, interfaces de repositories, use cases
- **Data** : DTOs, API Retrofit, implémentations de repositories, mappers

Pattern **MVVM** avec :
- **ViewModels** : Gestion d'état et logique métier
- **Compose UI** : Interface utilisateur déclarative
- **StateFlow** : Flux réactifs pour l'état UI

## Structure du Projet

```
allinconnect-android/
└── android/                          # Projet Android
    ├── app/                          # Module principal
    │   └── src/main/java/com/allinconnect/app/
    │       ├── core/                 # Services core (auth, network, location, etc.)
    │       ├── data/                # Couche data (API, DTOs, repositories)
    │       ├── domain/              # Couche domain (modèles, interfaces)
    │       └── presentation/        # Couche presentation (UI, ViewModels)
    ├── build.gradle.kts             # Configuration Gradle root
    └── settings.gradle.kts          # Configuration modules
```

## Technologies

- **Kotlin** : Langage de programmation
- **Jetpack Compose** : Framework UI déclaratif
- **Hilt** : Injection de dépendances
- **Retrofit + OkHttp** : Networking
- **Kotlinx Serialization** : Sérialisation JSON
- **Coroutines + Flow** : Programmation asynchrone
- **DataStore** : Stockage sécurisé (tokens)
- **Navigation Compose** : Navigation
- **Timber** : Logging
- **Coil** : Chargement d'images
- **Firebase Messaging** : Notifications push
- **Google Play Services Location** : Géolocalisation
- **Stripe Android SDK** : Paiements et abonnements

## Fonctionnalités

### Authentification
- Login, Signup, Forgot Password, Reset Password
- Gestion des tokens JWT (DataStore sécurisé)
- Gestion de session utilisateur

### Offres
- Liste des offres actives
- Détail d'une offre
- Filtres (ville, catégorie, type, dates)
- Recherche d'offres
- Gestion des offres Pro (création, modification, archivage)

### Partenaires
- Liste des professionnels
- Recherche par ville, catégorie, nom
- Recherche par géolocalisation (rayon)
- Détail partenaire avec avis
- Favoris (ajout/suppression)

### Ma Carte CLUB10
- Carte digitale CLUB10
- Statistiques d'économies
- Gestion famille (invitation, membres)
- Abonnements et paiements Stripe

### Profil
- Informations utilisateur
- Modification du profil
- Changement de mot de passe
- Préférences de notifications
- Paramètres

### Features Pro
- Gestion d'établissement
- Création/modification d'offres
- Historique des paiements
- Factures Stripe

### Billing & Payments
- Abonnements Stripe
- Payment Sheet
- Gestion des abonnements
- Portail client Stripe

### Autres
- Wallet (portefeuille)
- Savings (économies)
- Ratings (avis)
- Notifications push (Firebase)
- Cache local
- Gestion de la géolocalisation

## Configuration

### Prérequis

- **Android Studio** : Hedgehog (2023.1.1) ou supérieur
- **JDK** : 17 ou supérieur
- **Android SDK** : API 34 (Android 14)
- **Gradle** : 8.0+

### Installation

1. **Cloner le repository**
```bash
git clone <repository-url>
cd allinconnect-android
```

2. **Configurer les variables d'environnement**

Créez un fichier `android/local.properties` :

```properties
API_BASE_URL=http://127.0.0.1:8080/api/v1
API_BASE_URL_PRODUCTION=https://allinconnect-back-1.onrender.com/api/v1
```

3. **Configurer Firebase** (optionnel pour les notifications)

- Téléchargez `google-services.json` depuis Firebase Console
- Placez-le dans `android/app/`
- Le projet ID est : `allinconnect-a79b5`

4. **Ouvrir dans Android Studio**

```bash
cd android
# Ouvrir Android Studio et sélectionner le dossier android/
```

5. **Build et Run**

```bash
./gradlew assembleDebug
./gradlew installDebug
```

Ou utilisez Android Studio : **Run** (Shift+F10)

## Informations de l'App

- **Package** : `com.allinconnect.app`
- **Version** : 1.0
- **Version Code** : 1
- **Min SDK** : 24 (Android 7.0 Nougat)
- **Target SDK** : 34 (Android 14)
- **Compile SDK** : 34

## Backend API

L'application se connecte à l'API backend :

- **Local (Debug)** : `http://127.0.0.1:8080/api/v1`
- **Production** : `https://allinconnect-back-1.onrender.com/api/v1`

Les endpoints sont configurés dans `ApiConfig.kt` et peuvent être modifiés via `local.properties`.

### Endpoints Principaux

- **Auth** : `/auth/login`, `/auth/register`, `/auth/forgot-password`
- **Offers** : `/offers`, `/offers/{id}`
- **Partners** : `/partners`, `/partners/{id}`
- **Profile** : `/profile/me`, `/profile/update`
- **Billing** : `/billing/subscription`, `/billing/payment-sheet`
- **Subscriptions** : `/subscriptions/plans`, `/subscriptions/subscribe`

## Design System

Couleurs principales définies dans `android/app/src/main/res/values/colors.xml` :

- **Rouge principal** : `#BF2626` (app_red)
- **Rouge foncé** : `#1D0809`, `#421515`
- **Coral** : `#FF6666` (app_coral)
- **Or** : `#FFD700` (app_gold)
- **Fond** : Noir (`#000000`)

Thème Compose défini dans `presentation/theme/Theme.kt`.

## Tests

```bash
# Tests unitaires
./gradlew test

# Tests instrumentés
./gradlew connectedAndroidTest
```

## Build Variants

- **debug** : Version de développement avec suffixe `.debug`
- **release** : Version de production avec ProGuard activé

## Déploiement

### Build Release

```bash
./gradlew assembleRelease
```

L'APK sera généré dans `app/build/outputs/apk/release/`

### Signer l'APK

1. Générer une clé de signature
2. Configurer `signingConfigs` dans `build.gradle.kts`
3. Build avec `assembleRelease`

## Documentation

- `android/README.md` : Documentation détaillée Android
- `android/MIGRATION_COMPLETE.md` : État de la migration
- `android/FILES_DELETED.md` : Liste des fichiers Swift supprimés
- `android/VERIFICATION_COMPLETE.md` : Vérification finale

## Contribution

Ce projet est en développement actif. Pour contribuer :

1. Créer une branche depuis `main`
2. Faire vos modifications
3. Créer une Pull Request

## Licence

Propriétaire - Tous droits réservés

---

**Développé par** : Perrine Honoré  
**Dernière mise à jour** : Janvier 2026
