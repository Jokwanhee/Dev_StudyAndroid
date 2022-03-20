package com.example.gonistudy.ListView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.gonistudy.R

class ListViewAdapter(
    private val items: List<ListViewData>,
    private val layoutInflater: LayoutInflater
) : BaseAdapter(){
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        var view = convertView

        if (convertView == null){
            view = layoutInflater.inflate(R.layout.item_view_01,null)
        }

        val item : ListViewData = items[position]
        view!!.findViewById<TextView>(R.id.name_text_view).text = item.name
        view!!.findViewById<TextView>(R.id.hobby_text_view).text = item.hobby
        view!!.findViewById<TextView>(R.id.age_text_view).text = item.age.toString()

        return view
    }
}

