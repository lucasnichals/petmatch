package br.com.projects.petbite.viewmodel

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import br.com.projects.petbite.MainActivity
import br.com.projects.petbite.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_channel"
const val channelName = "br.com.projects.petbite_notifications"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            generateNotification(it.title!!, it.body!!)
        }
    }


    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews("br.com.projects.petbite", R.layout.pushnotification).apply {
            setTextViewText(R.id.tv_app_tittle, title)
            setTextViewText(R.id.tv_app_description, message)
            setImageViewResource(R.id.iv_app_logo, R.drawable.nova_logo2)
        }
        return remoteView
    }

    private fun generateNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(applicationContext, channelId).apply {
            setSmallIcon(R.drawable.nova_logo2)
            setAutoCancel(true)
            setOnlyAlertOnce(true)
            setContentIntent(pendingIntent)
            setContent(getRemoteView(title, message))
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
            }
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())
    }
}