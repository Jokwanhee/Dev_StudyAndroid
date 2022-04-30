package com.example.gonistudy.Alert.Calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityCalendarViewMainBinding

class CalendarViewMainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityCalendarViewMainBinding.inflate(layoutInflater)
    }

//    private lateinit var binding:ActivityCalendarViewMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar_view_main)

        binding.calendarBtn.setOnClickListener {
            val dialog = MyDialog(this)

            dialog.setOnDismissListener {
                Toast.makeText(
                    this, "${dialog.dayString}입니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            dialog.show()
        }
    }
}