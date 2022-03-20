package com.example.gonistudy.ListView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ListView
import com.example.gonistudy.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val frame = findViewById<ListView>(R.id.frame_list_view)

        val items = mutableListOf<ListViewData>()
        for (i in 0 until 10){
            items.add(ListViewData(i.toString(), i.toString(), i))
        }

        val adapter = ListViewAdapter(items, LayoutInflater.from(this))
        frame.adapter = adapter
        frame.setOnItemClickListener { adapterView, view, i, l ->
            Log.d("testing", "adapterView ${adapterView}")
            Log.d("testing", "view ${view}")
            Log.d("testing", "i $i")
            Log.d("testing", "l $l")
        }
    }
}