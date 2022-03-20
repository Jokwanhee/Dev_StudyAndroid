package com.example.gonistudy.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gonistudy.R

class RecyclerViewAdapter(
    private val items:List<RecyclerViewData>
):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name_text_view)
        val hobby = itemView.findViewById<TextView>(R.id.hobby_text_view)
        val age = itemView.findViewById<TextView>(R.id.age_text_view)
        fun bind(recyclerViewData: RecyclerViewData){
            name.text = recyclerViewData.name
            hobby.text = recyclerViewData.hobby
            age.text = recyclerViewData.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_01, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}