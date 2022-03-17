package com.example.gonistudy.Alert

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityAlertBinding

class AlertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alert)

        binding.alertBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder
                .setTitle("test title")
                .setMessage("test message")
                .create()
                .show()
        }

        binding.alertBtn2.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder
                .setTitle("test title")
                .setMessage("test message")
                .setPositiveButton("Positive", object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Toast.makeText(applicationContext,"Positive", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("Negative", object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Toast.makeText(applicationContext,"Negative", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNeutralButton("Neutral", object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Toast.makeText(applicationContext,"Neutral", Toast.LENGTH_SHORT).show()
                        application
                    }
                })
                .create()
                .show()
        }

        binding.alertBtn3.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            arrayListOf<String>()
            builder
                .setTitle("test title")
                .setItems(R.array.TEST, object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val items = resources.getStringArray(R.array.TEST)
                        Log.d("testing", "setItems item : ${items[p1]}")
                    }
                })
                .create()
                .show()
        }
        binding.alertBtn4.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val item = resources.getStringArray(R.array.TEST)
            val booleanArray = booleanArrayOf(true,false,true,true)
            builder
                .setTitle("test")
                .setMultiChoiceItems(R.array.TEST, booleanArray, object: DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
                        if (p2 == true){
                            Log.d("testing","setMultiChoiceItems item click on : ${item[p1]}")
                        } else {
                            Log.d("testing","setMultiChoiceItems item click off : ${item[p1]}")
                        }
                    }
                })
                .create()
                .show()
        }

        binding.alertBtn5.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val items = resources.getStringArray(R.array.TEST)
            builder
                .setTitle("test")
                .setSingleChoiceItems(R.array.TEST,4, object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Log.d("testing","setSingleChoiceItems item : ${items[p1]}")
                    }
                })
                .create()
                .show()
        }

        val alertBtn6 = findViewById<Button>(R.id.alert_btn6)
        alertBtn6.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder
                .setTitle("hi")
                .setView(R.layout.activity_alert_custom)
                .create()
                .show()
        }
    }
}