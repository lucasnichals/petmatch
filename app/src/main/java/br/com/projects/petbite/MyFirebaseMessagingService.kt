package br.com.projects.petbite

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.remoteMessage

const val channelId = "notification_channel"
const val channelName = "br.com.projects.petbite"
class MyFirebaseMessagingService : FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if(remoteMessage.notification != null){
            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        }

    }

    fun getRemoteView(tittle: String, message: String) : RemoteViews {
        val remoteView = RemoteViews("br.com.projects.petbite", R.layout.pushnotification)
        remoteView.setTextViewText(R.id.tv_app_tittle, tittle)
        remoteView.setTextViewText(R.id.tv_app_description, message)
        remoteView.setImageViewResource(R.id.iv_app_logo, R.drawable.ic_logo)

        return remoteView
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    fun generateNotification(tittle: String, message: String){

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_logo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(tittle,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    }
}