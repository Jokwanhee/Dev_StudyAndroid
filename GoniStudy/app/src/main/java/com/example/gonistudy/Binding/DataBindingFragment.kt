package com.example.gonistudy.Binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.gonistudy.R
import com.example.gonistudy.databinding.FragmentTestBinding

class DataBindingFragment:Fragment() {

    private lateinit var binding: FragmentTestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_test, container, false)
        return binding.root
    }
}