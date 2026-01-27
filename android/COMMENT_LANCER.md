# 🚀 Comment Lancer l'Application AllInConnect Android

## Méthode Simple: Android Studio

### Étape 1: Ouvrir le Projet

1. **Lancer Android Studio**
2. **File > Open** (ou `Cmd+O` sur Mac)
3. Naviguer et sélectionner le dossier **`android/`**
4. Cliquer sur **Open**

### Étape 2: Attendre la Synchronisation Gradle

- Android Studio va automatiquement synchroniser le projet
- **Première fois**: 5-10 minutes (téléchargement des dépendances)
- Attendre que la barre de progression en bas disparaisse
- Vérifier qu'il n'y a **pas d'erreurs** dans le panneau "Build"

### Étape 3: Choisir un Appareil

#### Option A: Appareil Physique (Téléphone Android)

1. Activer le **Mode développeur** sur votre téléphone:
   - Aller dans **Paramètres > À propos du téléphone**
   - Appuyer 7 fois sur **Numéro de build**
2. Activer le **Débogage USB**:
   - **Paramètres > Options développeur > Débogage USB**
3. Connecter le téléphone à votre Mac via USB
4. Autoriser le débogage USB sur le téléphone (popup)
5. Votre téléphone apparaîtra dans la liste des appareils en haut d'Android Studio

#### Option B: Créer un Émulateur

1. **Tools > Device Manager** (en haut d'Android Studio)
2. Cliquer sur **Create Device**
3. Choisir un appareil (ex: **Pixel 7**)
4. Cliquer sur **Next**
5. Choisir une **System Image**: **API 34** (Android 14) ou **API 33**
   - Si pas installé, cliquer sur **Download** à côté
   - Attendre le téléchargement
6. Cliquer sur **Next** puis **Finish**
7. Cliquer sur le bouton **Play** (▶️) à côté de l'émulateur pour le démarrer
8. Attendre que l'émulateur démarre complètement

### Étape 4: Lancer l'Application

1. **Sélectionner votre appareil/émulateur** dans la liste déroulante en haut (à côté du bouton Run)
2. Cliquer sur le bouton **Run** (▶️ vert) ou appuyer sur **Shift+F10** (Mac: `Shift+Fn+F10`)
3. L'app va compiler (première fois: 2-5 minutes)
4. L'app va s'installer automatiquement sur l'appareil
5. L'app se lancera automatiquement

---

## ✅ Vérifier que ça Fonctionne

### 1. Vérifier la Connexion Backend

1. Lancer l'app
2. Dans Android Studio, ouvrir **Logcat** (onglet en bas)
3. Filtrer par tag: `OkHttp`
4. Rechercher: `allinconnect-back-1.onrender.com`
5. Vous devriez voir les requêtes HTTP vers le backend

**Si vous voyez les requêtes** → ✅ **Backend connecté !**

### 2. Tester l'Application

1. **Splash Screen** → S'affiche au démarrage
2. **Tutorial** → Si première utilisation
3. **Welcome** → Choisir Login ou SignUp
4. **Login/SignUp** → Se connecter ou créer un compte
5. **TabBar** → Navigation entre Home, Offers, Card, Profile

---

## 🐛 Problèmes Courants

### "Gradle sync failed"

**Solution**:
1. **File > Invalidate Caches / Restart**
2. Sélectionner **Invalidate and Restart**
3. Attendre le redémarrage
4. **File > Sync Project with Gradle Files**

### "SDK not found"

**Solution**:
1. **Tools > SDK Manager**
2. Installer **Android SDK Platform 34**
3. Installer **Android SDK Build-Tools**
4. Synchroniser à nouveau

### "Device not found" ou "No devices"

**Pour appareil physique**:
- Vérifier que le débogage USB est activé
- Vérifier que le câble USB fonctionne
- Essayer de débrancher/rebrancher le câble

**Pour émulateur**:
- Vérifier que l'émulateur est démarré (bouton Play ▶️)
- Attendre que l'émulateur soit complètement chargé

### "Build failed"

**Solution**:
1. **Build > Clean Project**
2. **Build > Rebuild Project**
3. Attendre la fin du build

### L'app ne se connecte pas au backend

**Vérifications**:
1. Vérifier la connexion internet (appareil/émulateur)
2. Vérifier Logcat pour les erreurs réseau
3. Vérifier que le backend Render est actif: https://allinconnect-back-1.onrender.com/api/v1

---

## 📱 Informations Importantes

- **Package**: `com.allinconnect.app`
- **Backend**: `https://allinconnect-back-1.onrender.com/api/v1` (déjà configuré)
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

---

## 🎯 Résumé Rapide

1. **Ouvrir Android Studio**
2. **File > Open** → Sélectionner `android/`
3. **Attendre Gradle sync** (5-10 min première fois)
4. **Tools > Device Manager** → Créer/choisir un appareil
5. **Run** (▶️) → L'app se lance !

---

**C'est tout ! L'app est prête à être lancée.** 🚀
