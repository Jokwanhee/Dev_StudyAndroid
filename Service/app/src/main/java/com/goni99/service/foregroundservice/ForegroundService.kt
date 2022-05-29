package com.goni99.service.foregroundservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.goni99.service.R
import kotlin.concurrent.thread

class ForegroundService : Service() {

    val CHANNEL_ID = "FGS153"
    val NOTI_ID = 153

    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(CHANNEL_ID, "FOREGROUND", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()

        startForeground(NOTI_ID, notification)
        runBackground()
        return super.onStartCommand(intent, flags, startId)

    }

    fun runBackground(){
        thread {
            for(i in 0..100) {
                Thread.sleep(1000)
                Log.d("testing", "count ---> $i")
            }
        }
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}