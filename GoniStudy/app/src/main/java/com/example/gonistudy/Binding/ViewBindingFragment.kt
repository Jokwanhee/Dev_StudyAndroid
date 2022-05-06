package com.example.gonistudy.Binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.gonistudy.R
import com.example.gonistudy.databinding.FragmentTestBinding


/*
    1. build.gradle -> buildFeatures -> viewBinding true
    2. mBinding 객체 생성
    3. binding 고정 변수 설정
*/


class ViewBindingFragment : Fragment() {

    private var mBinding: FragmentTestBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTestBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}