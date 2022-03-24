package com.example.gonistudy.ViewBinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityViewBindingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            binding.textView.text = "Hello View Binding!"
        }

    }
}