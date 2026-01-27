# AllInConnect - Application Android

Application Android native pour AllInConnect, développée avec Kotlin et Jetpack Compose.

## 📋 Prérequis

- **Android Studio**: Hedgehog (2023.1.1) ou supérieur
- **JDK**: 17 ou supérieur
- **Android SDK**: API 34 (Android 14)
- **Gradle**: 8.0+ (inclus dans le projet)

## 🚀 Démarrage Rapide

### 1. Ouvrir le Projet

1. Ouvrir **Android Studio**
2. **File > Open** > Sélectionner le dossier `android/`
3. Attendre la synchronisation Gradle (première fois: 5-10 minutes)

### 2. Configurer l'Émulateur ou Appareil

- **Tools > Device Manager**
- **Create Device** > Choisir un appareil (ex: Pixel 7)
- **System Image**: API 34 (Android 14)

### 3. Lancer l'Application

- **Run** (Shift+F10) ou cliquer sur le bouton ▶️

### Ligne de Commande

```bash
cd android
chmod +x gradlew
./gradlew assembleDebug
./gradlew installDebug
```

## 🌐 Configuration Backend

### Backend Render (Production)

**URL**: `https://allinconnect-back-1.onrender.com/api/v1`

Le projet est configuré pour utiliser le backend Render par défaut.

**Configuration**:
- `gradle.properties`: Définit `API_BASE_URL_PRODUCTION`
- `build.gradle.kts`: BuildConfig avec fallback vers Render si `API_BASE_URL` non défini
- `ApiConfig.kt`: Utilise `BuildConfig.API_BASE_URL`
- `NetworkModule.kt`: Configure Retrofit avec cette URL

**Par défaut**: Si `API_BASE_URL` n'est pas défini dans `gradle.properties`, l'app utilise automatiquement Render.

### Utiliser un Backend Local

Modifier `android/gradle.properties`:
```properties
API_BASE_URL=http://127.0.0.1:8080/api/v1
```

Pour un appareil physique, utiliser l'IP de votre machine:
```properties
API_BASE_URL=http://192.168.1.X:8080/api/v1
```

Puis rebuild:
```bash
./gradlew clean
./gradlew assembleDebug
```

### Vérifier la Connexion Backend

1. Lancer l'app
2. Ouvrir **Logcat** dans Android Studio
3. Filtrer par tag: `OkHttp`
4. Rechercher: `allinconnect-back-1.onrender.com`
5. Vérifier les requêtes HTTP (200 = OK)

## 📱 Informations de l'App

- **Package**: `com.allinconnect.app`
- **Version**: 1.0
- **Min SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34

## 🏗️ Architecture

- **Langage**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture (Presentation / Domain / Data)
- **Pattern**: MVVM
- **DI**: Hilt
- **Networking**: Retrofit + OkHttp
- **JSON**: Kotlinx Serialization
- **Async**: Kotlin Coroutines + Flow
- **Persistence**: DataStore (tokens/settings)
- **Logging**: Timber
- **Build**: Gradle Kotlin DSL

## 📦 Structure du Projet

```
android/
├── app/
│   └── src/main/java/com/allinconnect/app/
│       ├── core/              # Services core (auth, network, location, etc.)
│       ├── data/              # Couche data (API, DTOs, repositories)
│       ├── domain/            # Modèles domain
│       └── presentation/       # UI (screens, ViewModels, navigation)
│           ├── auth/          # Écrans d'authentification
│           ├── billing/       # Écrans de facturation
│           ├── card/          # Écrans de carte
│           ├── home/          # Écran d'accueil
│           ├── offer/         # Détail offre
│           ├── offers/        # Liste offres
│           ├── onboarding/    # Onboarding
│           ├── partner/       # Détail partenaire
│           ├── partners/      # Liste partenaires
│           ├── pro/           # Écrans professionnels
│           ├── profile/       # Écrans profil
│           ├── splash/        # Splash screen
│           ├── tabs/          # Tab bar
│           └── components/    # Composants UI réutilisables
```

## ✅ Fonctionnalités

### Authentification
- ✅ Login / SignUp
- ✅ Forgot Password
- ✅ Gestion des tokens (DataStore sécurisé)

### Offres & Partenaires
- ✅ Liste des offres
- ✅ Détail d'une offre
- ✅ Liste des partenaires
- ✅ Détail d'un partenaire
- ✅ Recherche de partenaires

### Carte CLUB10
- ✅ Informations de la carte
- ✅ Gestion de la carte famille
- ✅ Liste des économies
- ✅ Statistiques

### Profil
- ✅ Édition du profil
- ✅ Changement de mot de passe
- ✅ Préférences de notifications
- ✅ Paramètres
- ✅ Parrainage

### Abonnements & Paiements
- ✅ Abonnement client/pro
- ✅ Gestion des abonnements
- ✅ Stripe Payment Sheet
- ✅ Stripe Subscription Payment Sheet

### Features Pro
- ✅ Gestion des offres
- ✅ Création d'offres
- ✅ Gestion de l'établissement
- ✅ Historique des paiements
- ✅ Factures
- ✅ Gestion des abonnements clients

## 📊 Endpoints Backend

Tous les **43 endpoints** sont configurés et connectés:

- Auth: 4 endpoints
- Offers: 6 endpoints
- Partners: 3 endpoints
- Profile: 5 endpoints
- Billing: 4 endpoints
- Subscriptions: 4 endpoints
- Savings: 4 endpoints
- Favorites: 3 endpoints
- Ratings: 2 endpoints
- Wallet: 2 endpoints
- Notification Preferences: 2 endpoints
- Invoices: 1 endpoint
- Payment: 2 endpoints
- Push: 1 endpoint

## 🔐 Sécurité

- **Token Storage**: DataStore sécurisé (équivalent Keychain iOS)
- **Token Management**: Ajout automatique dans headers HTTP via `AuthInterceptor`
- **Permissions**: Toutes déclarées dans `AndroidManifest.xml`

## 🔥 Firebase (Optionnel)

Pour activer les notifications push:

1. Télécharger `google-services.json` depuis Firebase Console
2. Le placer dans `android/app/`
3. Project ID: `allinconnect-a79b5`

**Note**: L'app fonctionne sans Firebase, mais les notifications push ne seront pas disponibles.

## 🐛 Dépannage

### Build échoue
```bash
cd android
./gradlew clean
./gradlew assembleDebug
```

### Gradle sync échoue
1. **File > Invalidate Caches / Restart**
2. **File > Sync Project with Gradle Files**

### App ne se connecte pas au backend
1. Vérifier Logcat (tag `OkHttp`)
2. Vérifier que l'URL contient `allinconnect-back-1.onrender.com`
3. Vérifier la connexion internet
4. Vérifier que le backend Render est actif

## 📚 Dépendances Principales

- **Compose**: BOM 2023.10.01
- **Hilt**: 2.48
- **Retrofit**: 2.9.0
- **OkHttp**: 4.12.0
- **Kotlinx Serialization**: 1.6.0
- **Coroutines**: 1.7.3
- **DataStore**: 1.0.0
- **Stripe**: 20.37.1
- **Firebase**: BOM 32.7.0

## ✅ Checklist de Démarrage

- [ ] Android Studio installé
- [ ] JDK 17 installé
- [ ] Android SDK 34 installé
- [ ] Projet ouvert dans Android Studio
- [ ] Gradle sync réussi
- [ ] Appareil/émulateur connecté
- [ ] App lancée avec succès
- [ ] Backend Render accessible (vérifier Logcat)
- [ ] Login fonctionne

## 📝 Notes

- Les logs HTTP sont activés en debug (voir `NetworkModule.kt`)
- Le token est automatiquement ajouté dans les headers HTTP
- Tous les écrans sont accessibles via la navigation
- Tous les ViewModels sont injectés via Hilt

---

**Le projet est prêt à être lancé !** 🚀
