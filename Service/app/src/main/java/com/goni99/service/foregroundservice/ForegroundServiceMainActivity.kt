package com.goni99.service.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.goni99.service.R

class ForegroundServiceMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service_main)
    }

    fun serviceStart(view:View){
        val intent = Intent(this, ForegroundService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }

    fun serviceStop(view:View){
        val intent = Intent(this, ForegroundService::class.java)
        stopService(intent)
    }
}