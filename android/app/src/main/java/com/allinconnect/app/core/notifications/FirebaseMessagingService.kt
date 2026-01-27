package com.allinconnect.app.core.notifications

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMessagingService : FirebaseMessagingService() {
    
    @Inject
    lateinit var pushManager: PushManager
    
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        pushManager.handleDeviceToken(token)
    }
    
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // Handle notification
    }
}
