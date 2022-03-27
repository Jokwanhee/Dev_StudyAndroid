package com.example.gonistudy.CustomView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityCustomViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val customView = CustomView(this)
        binding.customViewFrameLayout.addView(customView)
    }
}