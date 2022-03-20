package com.example.gonistudy.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gonistudy.R

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val frame = findViewById<RecyclerView>(R.id.frame_recycler_view)

        val items = mutableListOf<RecyclerViewData>()
        for (i in 0 until 10){
            items.add(RecyclerViewData(i.toString(), i.toString(), i))
        }

        val adapter = RecyclerViewAdapter(items)

        frame.adapter = adapter
        frame.layoutManager = LinearLayoutManager(this)
    }
}