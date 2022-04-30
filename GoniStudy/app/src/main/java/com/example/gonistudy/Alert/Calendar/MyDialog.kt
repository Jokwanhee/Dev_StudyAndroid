package com.example.gonistudy.Alert.Calendar

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CalendarView
import com.example.gonistudy.R

class MyDialog(
    ctx: Context
): Dialog(ctx) {
    open var dayString = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_dialog)

        val calendarView = findViewById<CalendarView>(R.id.calendar_view)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dayString = "${year}-${month}-${dayOfMonth}"
            dismiss()x
        }
    }
}