package com.example.gonistudy.ViewPager_Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gonistudy.databinding.ActivityViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityViewPagerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 페이지 데이터 로드
        val fragments =
            listOf<Fragment>(PageAFragment(), PageBFragment(), PageCFragment(), PageDFragment())

        // 2. 어뎁터 생성
        val pageAdapter = FragmentPagerAdapter(fragments, this)

        // 3. 생성된 어뎁터와 뷰 페이저의 어뎁터 연결
        binding.viewPager.adapter = pageAdapter

        // 4. 탭 레이아웃의 원하는 제목 크기만큼 작성
        val titles = listOf<String>("A", "B", "C", "D")
        // 5. 탭 레이아웃과 뷰 페이저 연결 및 탭 제목 적용
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}

class FragmentPagerAdapter(val fragments: List<Fragment>, fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}