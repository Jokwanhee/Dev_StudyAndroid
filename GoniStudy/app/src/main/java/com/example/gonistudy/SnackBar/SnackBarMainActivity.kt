package com.example.gonistudy.SnackBar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivitySnackBarMainBinding
import com.google.android.material.snackbar.Snackbar

class SnackBarMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySnackBarMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_snack_bar_main)

        binding.snackBtn.setOnClickListener {
            Snackbar.make(binding.linearLayout, "스낵바 호출 중...", Snackbar.LENGTH_SHORT)
                .setAction("확인", {
                    Snackbar.make(it, "확인 눌름", Snackbar.LENGTH_SHORT)
                        .show()
                })
                .setTextColor(Color.RED)
                .setActionTextColor(Color.RED)
                .setBackgroundTint(Color.LTGRAY)
                .show()
        }
    }
}