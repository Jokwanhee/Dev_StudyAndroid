package com.example.gonistudy.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySubBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val text = intent.getStringExtra("text")
        val number = intent.getIntExtra("number",0)
        with(binding){
            text1.text = text
            text2.text = number.toString()
        }


        binding.subButton.setOnClickListener {
            val message = binding.editText.text.toString()
            val intent = Intent()
            intent.putExtra("message",message)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}