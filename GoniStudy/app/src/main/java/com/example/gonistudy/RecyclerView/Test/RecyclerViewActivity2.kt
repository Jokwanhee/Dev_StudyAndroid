package com.example.gonistudy.RecyclerView.Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityRecyclerView2Binding
import com.example.gonistudy.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerViewActivity2 : AppCompatActivity() {

    val binding by lazy {
        ActivityRecyclerView2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val memoList = mutableListOf<Memo>()
        for (i in 0..100) {
            val title  = "Android $i"
            val date = simpleDateFormat(System.currentTimeMillis())
            memoList.add(Memo(i, title, date))

        }
        Log.d("testing", "$memoList")
        val adapter = CustomAdapter(memoList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}


data class Memo(
    val number: Int,
    val title: String,
    val date: String
)

fun simpleDateFormat(date:Long): String{
    val simple = SimpleDateFormat("yyyy-MM-dd")
    return simple.format(date)
}


class CustomAdapter(
    val memoList : List<Memo>
): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    class ViewHolder(val binding:ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(memo:Memo){
            with(binding){
                number.text = memo.number.toString()
                title.text = memo.title
                date.text = memo.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(memoList[position])
    }

    override fun getItemCount(): Int = memoList.size
}