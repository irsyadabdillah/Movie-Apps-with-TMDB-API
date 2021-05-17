package com.irzstudio.movieapps.service

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        val refreshToken = FirebaseInstanceId.getInstance().token
        Log.e("refreshToken", refreshToken!!)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        showNotification(p0.notification?.title.toString(), p0.notification?.body.toString())

        val checkToken = FirebaseInstanceId.getInstance().token
        Log.d("checkToken", checkToken!!)
    }

    fun showNotification(title: String, message: String){
        val builder = NotificationCompat.Builder(this, "notification")
            .setContentTitle(title)
            .setContentText(message)
        val manager = NotificationManagerCompat.from(this)
        manager.notify(1, builder.build())
    }
}