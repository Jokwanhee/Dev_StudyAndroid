package com.example.gonistudy.ViewPager_RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityViewPagerRecyclerBinding
import com.example.gonistudy.databinding.ItemViewpagerRecyclerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerRecyclerActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityViewPagerRecyclerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val items = loadData()
        val titles = listOf<String>("월", "화", "수", "목", "금", "토", "일")
        val recyclerAdapter = CustomRecyclerAdapter(items)
        binding.viewPager.adapter = recyclerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    fun loadData(): List<PageData> {
        val items = mutableListOf<PageData>()
        items.apply {
            add(PageData(1, "맑음"))
            add(PageData(2, "소나기"))
            add(PageData(3, "눈"))
            add(PageData(4, "따뜻함"))
            add(PageData(5, "추움"))
            add(PageData(6, "비"))
            add(PageData(7, "흐림"))
        }
        return items
    }
}

class CustomRecyclerAdapter(val items: List<PageData>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemViewpagerRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pageData: PageData) {
            binding.dateTextView.text = pageData.date.toString() + "일"
            binding.weatherTextView.text = pageData.weather
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemViewpagerRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}

data class PageData(
    val date: Int,
    val weather: String
)