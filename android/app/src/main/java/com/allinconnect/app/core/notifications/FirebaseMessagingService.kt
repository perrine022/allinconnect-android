package com.allinconnect.app.core.notifications

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMessagingService : FirebaseMessagingService() {
    
    @Inject
    lateinit var pushManager: PushManager
    
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Enregistrer le token FCM avec le backend
        CoroutineScope(Dispatchers.IO).launch {
            pushManager.registerFCMToken(token)
        }
    }
    
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        
        // Gérer les notifications reçues
        remoteMessage.notification?.let { notification ->
            // Afficher la notification
            // TODO: Implémenter l'affichage de notification
        }
        
        // Gérer les données de la notification
        remoteMessage.data.let { data ->
            // Extraire les données (ex: offerId)
            data["offerId"]?.let { offerId ->
                // Naviguer vers l'offre si nécessaire
            }
        }
    }
}

