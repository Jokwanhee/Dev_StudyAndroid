package com.example.gonistudy.Room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gonistudy.R

class RoomRecyclerViewAdapter(
    val items: List<RoomMemo>
) : RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val no = itemView.findViewById<TextView>(R.id.room_number_text_view)
        val content = itemView.findViewById<TextView>(R.id.room_content_text_view)
        val date = itemView.findViewById<TextView>(R.id.room_date_text_view)
        fun bind(roomMemo: RoomMemo){
            no.text = roomMemo.no.toString()
            content.text = roomMemo.content
            date.text = roomMemo.simpleFormat(roomMemo.datetime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_room, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}