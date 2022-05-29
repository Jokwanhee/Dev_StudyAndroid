package com.example.gonistudy.Notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat.from
import com.example.gonistudy.R

class NotificationActivity : AppCompatActivity() {
    private lateinit var notificationManager: NotificationManager

    companion object {
        const val CHANNEL_ID = "test_channel_id"
        const val CHANNEL_NAME = "test_channel_name"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        createChannel()

        val notificationBtn = findViewById<Button>(R.id.notification_btn)
        notificationBtn.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("test")
                .setContentText("test tt")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                // Android 7.1 이하를 지원하려면 아래에 표시된 대로 setPriority 설정해야 함
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true) // 알림 클릭 시 사라짐
            with(from(this@NotificationActivity)){
                notify(NOTIFICATION_ID, builder.build())
            }
        }
    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel Description"
            }
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}