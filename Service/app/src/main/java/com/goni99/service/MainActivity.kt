package com.goni99.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceIntent = Intent(this, MyService::class.java)
    }

    fun serviceStart(view:View) {
        serviceIntent.action = MyService.ACTION_CREATE
        startService(intent)
    }

    fun serviceStop(view:View) {
        stopService(intent)
    }

    var myService: MyService? = null
    var isService = false
    val connection = object : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {
            isService = true
            val binder = iBinder as MyService.MyBinder
            myService = binder.getService()
        }
    }

    fun serviceBind(view:View){
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }
    fun serviceCommand(){
        myService?.create()
        myService?.delete()
    }
    fun serviceUnBind(view:View){

    }

}