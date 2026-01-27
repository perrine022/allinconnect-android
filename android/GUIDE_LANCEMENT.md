# 🚀 Guide de Lancement - AllInConnect Android

## Méthode 1: Android Studio (Recommandé)

### Étape 1: Ouvrir le Projet

1. **Lancer Android Studio**
2. **File > Open** (ou `Cmd+O` sur Mac)
3. Naviguer vers le dossier `android/` et le sélectionner
4. Cliquer sur **Open**

### Étape 2: Attendre la Synchronisation

- Android Studio va automatiquement synchroniser Gradle
- **Première fois**: Cela peut prendre 5-10 minutes
- Attendre que la barre de progression en bas disparaisse
- Vérifier qu'il n'y a pas d'erreurs dans le panneau "Build"

### Étape 3: Configurer un Appareil/Émulateur

#### Option A: Utiliser un Appareil Physique

1. Activer le **Mode développeur** sur votre téléphone Android
2. Activer le **Débogage USB**
3. Connecter le téléphone à votre Mac via USB
4. Autoriser le débogage USB sur le téléphone
5. Votre appareil apparaîtra dans la liste des appareils en haut d'Android Studio

#### Option B: Créer un Émulateur

1. **Tools > Device Manager**
2. Cliquer sur **Create Device**
3. Choisir un appareil (ex: **Pixel 7**)
4. Cliquer sur **Next**
5. Choisir une **System Image**: **API 34 (Android 14)** ou **API 33**
   - Si pas installé, cliquer sur **Download** à côté
6. Cliquer sur **Next** puis **Finish**
7. L'émulateur apparaîtra dans la liste des appareils

### Étape 4: Lancer l'Application

1. Sélectionner votre appareil/émulateur dans la liste en haut
2. Cliquer sur le bouton **Run** (▶️) ou appuyer sur **Shift+F10**
3. L'app va compiler et s'installer sur l'appareil
4. L'app se lancera automatiquement

---

## Méthode 2: Ligne de Commande

### Prérequis

- Android SDK installé (via Android Studio)
- `adb` dans le PATH (généralement dans `~/Library/Android/sdk/platform-tools/`)

### Étape 1: Aller dans le Dossier Android

```bash
cd /Users/perrinehonore/allinconnect-android/android
```

### Étape 2: Donner les Permissions au Gradlew (Première Fois)

```bash
chmod +x gradlew
```

### Étape 3: Vérifier qu'un Appareil est Connecté

```bash
adb devices
```

Vous devriez voir votre appareil listé. Si rien n'apparaît:
- Vérifier que le débogage USB est activé
- Vérifier que le câble USB fonctionne
- Essayer `adb kill-server && adb start-server`

### Étape 4: Compiler l'Application

```bash
./gradlew assembleDebug
```

Cela va créer un fichier APK dans `app/build/outputs/apk/debug/app-debug.apk`

### Étape 5: Installer sur l'Appareil

```bash
./gradlew installDebug
```

### Étape 6: Lancer l'Application

```bash
adb shell am start -n com.allinconnect.app/.presentation.MainActivity
```

---

## Méthode 3: Build et Install en Une Commande

```bash
cd /Users/perrinehonore/allinconnect-android/android
chmod +x gradlew
./gradlew installDebug && adb shell am start -n com.allinconnect.app/.presentation.MainActivity
```

---

## 🔧 Configuration Backend

### Backend Render (Production) - DÉJÀ CONFIGURÉ ✅

L'app est **déjà configurée** pour utiliser le backend Render:
- **URL**: `https://allinconnect-back-1.onrender.com/api/v1`

**Par défaut**, l'app utilise automatiquement Render si `API_BASE_URL` n'est pas défini dans `gradle.properties`.

### Vérifier la Connexion Backend

1. Lancer l'app
2. Dans Android Studio, ouvrir **Logcat** (en bas)
3. Filtrer par tag: `OkHttp`
4. Rechercher: `allinconnect-back-1.onrender.com`
5. Vous devriez voir les requêtes HTTP vers le backend

---

## 🐛 Dépannage

### Problème: "Gradle sync failed"

**Solution**:
1. **File > Invalidate Caches / Restart**
2. Sélectionner **Invalidate and Restart**
3. Attendre le redémarrage
4. **File > Sync Project with Gradle Files**

### Problème: "SDK not found"

**Solution**:
1. **Tools > SDK Manager**
2. Installer **Android SDK Platform 34**
3. Installer **Android SDK Build-Tools**
4. Synchroniser à nouveau

### Problème: "Device not found"

**Solution**:
```bash
adb kill-server
adb start-server
adb devices
```

### Problème: "Build failed"

**Solution**:
```bash
cd android
./gradlew clean
./gradlew assembleDebug
```

### Problème: L'app ne se connecte pas au backend

**Vérifications**:
1. Vérifier la connexion internet
2. Vérifier Logcat pour les erreurs réseau
3. Vérifier que le backend Render est actif: https://allinconnect-back-1.onrender.com/api/v1

---

## ✅ Checklist de Lancement

- [ ] Android Studio installé
- [ ] JDK 17 installé
- [ ] Android SDK 34 installé
- [ ] Projet ouvert dans Android Studio
- [ ] Gradle sync réussi (pas d'erreurs)
- [ ] Appareil/émulateur connecté et visible
- [ ] App compilée avec succès
- [ ] App installée sur l'appareil
- [ ] App lancée et fonctionne

---

## 📱 Première Utilisation

1. **Splash Screen** → Vérifie si vous êtes connecté
2. **Tutorial** → Si première utilisation
3. **Welcome** → Choisir Login ou SignUp
4. **Login/SignUp** → Se connecter ou créer un compte
5. **TabBar** → Accès aux écrans principaux

---

## 🎯 Test Rapide

Pour tester rapidement:
1. Lancer l'app
2. Aller sur **Login**
3. Vérifier **Logcat** (tag `OkHttp`)
4. Vous devriez voir les requêtes vers `allinconnect-back-1.onrender.com`

Si vous voyez les requêtes HTTP → ✅ **Backend connecté !**

---

**L'app est prête à être lancée !** 🚀
