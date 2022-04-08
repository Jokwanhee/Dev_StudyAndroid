package com.example.gonistudy.SQLite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gonistudy.R

class SqliteRecyclerAdapter(
    val items: List<Memo>
) : RecyclerView.Adapter<SqliteRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val no = itemView.findViewById<TextView>(R.id.sql_number_text_view)
        val content = itemView.findViewById<TextView>(R.id.sql_content_text_view)
        val datetime = itemView.findViewById<TextView>(R.id.sql_date_text_view)

        fun bind(memo: Memo) {
            no.text = memo.no.toString()
            content.text = memo.content
            datetime.text = memo.simpleFormat(memo.datetime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_sql, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}