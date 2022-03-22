package com.example.gonistudy.ListViewUpgrade

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat.from
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityList2Binding

class ListActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityList2Binding
    private lateinit var adapter: ListAdapter
    private lateinit var notificationManager: NotificationManager
    val item = mutableListOf<ListData>()

    companion object{
        const val CHANNEL_ID = "channel01"
        const val CHANNEL_NAME = "test_channel01"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list2)

        createChannel()
        binding.dataInsertBtn.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val hobby = binding.hobbyEditText.text.toString()
            val age = binding.ageEditText.text.toString().toInt()
            val listData = ListData(name, hobby, age)
            createListViewItem(listData)
        }

    }

    override fun onStart() {
        super.onStart()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_ID)
    }

    fun createListViewItem(listData: ListData) {
        item.add(listData)

        adapter = ListAdapter(item)
        binding.frameListView.adapter = adapter
        binding.frameListView.setOnItemClickListener { adapterView, view, i, l ->
            val dialog = CustomDialog(this)
            dialog.showDialog()
            dialog.setOnClickListener(object:CustomDialog.OnDialogClickListener{
                override fun onInsertClicked(name: String, hobby: String, age: Int) {
                    val newListData = ListData(name, hobby, age)
                    item[i] = newListData
                    adapter = ListAdapter(item)
                    binding.frameListView.adapter = adapter
                    createNotificationCompat()
                }

                override fun onDeleteClicked() {
                    item.removeAt(i)
                    adapter = ListAdapter(item)
                    binding.frameListView.adapter = adapter
                }

            })
        }
    }

    fun createNotificationCompat(){
        val intent = Intent(this, ListActivity2::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this,0,intent,0)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Notification")
            .setContentText("Add New ListView item")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
        notificationManager.notify(NOTIFICATION_ID,builder.build())
//        with(from(this)){
//            notify(NOTIFICATION_ID,builder.build())
//        }
    }

    fun createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "test channel description"
            }
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}