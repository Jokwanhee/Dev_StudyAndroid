package com.example.gonistudy.Binding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityMainBaseBinding

/*
    1. build.gradle -> buildFeatures -> viewBinding true
    2. binding 객체 생성
    3. binding 객체에 해당 레이아웃 설정하기
*/

class ViewBindingActivity:AppCompatActivity() {

    val binding by lazy {
        ActivityMainBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}