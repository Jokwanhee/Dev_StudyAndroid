package com.example.gonistudy.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityPracticeBinding

class PracticeActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPracticeBinding.inflate(layoutInflater)
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val intent = it.data
            val editData = intent?.getStringExtra("message")
            binding.textView.text = editData
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.praticeButton.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

        binding.dataButton.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("text","get string intent")
            intent.putExtra("number",123456)
            startForResult.launch(intent)
        }
    }


}