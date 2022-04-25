package com.example.gonistudy.Thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityThreadMainBinding
import kotlin.concurrent.thread

class ThreadMainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityThreadMainBinding.inflate(layoutInflater)
    }

    var total = 0
    var started = false

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val minute = String.format("%02d", total / 60)
            val second = String.format("%02d", total % 60)

            binding.timerTextView.text = "$minute:$second"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            started = true
            thread(start=true){
                while (started){
                    Thread.sleep(1000)

                    if (started){
                        total = total + 1
                        runOnUiThread {
                            val minute = String.format("%02d", total / 60)
                            val second = String.format("%02d", total % 60)

                            binding.timerTextView.text = "$minute:$second"
                        }
//                        handler?.sendEmptyMessageAtTime(0, 1000)
                    }
                }
            }
        }

        binding.endButton.setOnClickListener {
            if (started){
                started = false
                total = 0
                binding.timerTextView.text = "00:00"
            }
        }


    }
}