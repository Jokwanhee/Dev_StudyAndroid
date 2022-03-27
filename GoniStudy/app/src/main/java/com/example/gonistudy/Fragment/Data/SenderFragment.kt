package com.example.gonistudy.Fragment.Data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.gonistudy.databinding.FragmentSenderBinding

class SenderFragment: Fragment() {
    lateinit var binding:FragmentSenderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            yesButton.setOnClickListener {
                val bundle = bundleOf("changeData" to "YES")
                setFragmentResult("request",bundle)
            }
            noButton.setOnClickListener {
                val bundle = bundleOf("changeData" to "NO")
                setFragmentResult("request", bundle)
            }
        }



    }

}