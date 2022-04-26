package com.example.gonistudy.Alert.Custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityCustomMainBinding

class CustomMainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityCustomMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.customAlertButton.setOnClickListener {
            val dialog = CustomDialog(this@CustomMainActivity)
            dialog.showDialog()
            dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener{
                override fun onClicked(name: String) {
                    binding.customTextView.text = name
                }
            })
        }
    }
}