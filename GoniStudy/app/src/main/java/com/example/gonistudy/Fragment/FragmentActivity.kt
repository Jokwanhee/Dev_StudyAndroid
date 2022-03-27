package com.example.gonistudy.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFragmentBinding.inflate(layoutInflater)
    }
    val listFragment by lazy {
        ListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showList()

        binding.sendButton.setOnClickListener {
            val makeText = "send from activity"
            listFragment.sendData(makeText)
        }
    }

    fun showList(){
        val bundle = Bundle()
        bundle.putString("key","bundle로 string 전달")
        bundle.putInt("value",12345)


        listFragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_frame_layout, listFragment)
            .commit()
    }

    fun goDetail(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_frame_layout, DetailFragment())
            .addToBackStack("detail")
            .commit()
    }

    fun backDetail(){
        onBackPressed()
    }
}