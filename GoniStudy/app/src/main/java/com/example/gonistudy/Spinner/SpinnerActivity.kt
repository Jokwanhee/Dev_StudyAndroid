package com.example.gonistudy.Spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivitySpinnerBinding

class SpinnerActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySpinnerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val items = listOf<String>("====선택하세요====", "First", "Second", "Third")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
        binding.spinner.adapter = adapter

        with(binding){
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selected = items.get(p2)
                    binding.resultTextView.text = selected
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
    }
}