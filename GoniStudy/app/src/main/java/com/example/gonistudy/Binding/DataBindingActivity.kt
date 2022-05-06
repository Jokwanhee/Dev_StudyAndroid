package com.example.gonistudy.Binding

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityMainBaseBinding

/*
    1. build.gradle -> buildFeatures -> dataBinding true
    2. plugins -> 'kotlin-kapt'
    3. 원하는 레이아웃 <layout></layout>으로 엮는다.
    4. binding 객체 생성
    5. DataBindingUtil.setContentView
*/

class DataBindingActivity:AppCompatActivity() {

    private lateinit var binding:ActivityMainBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_base)

    }
}