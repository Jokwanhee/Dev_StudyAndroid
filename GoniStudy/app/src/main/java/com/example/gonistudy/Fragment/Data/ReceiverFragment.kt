package com.example.gonistudy.Fragment.Data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.gonistudy.databinding.FragmentReceiverBinding

class ReceiverFragment:Fragment() {
    lateinit var binding:FragmentReceiverBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("request"){ key, bundle ->
            bundle.getString("changeData").let {
                binding.sendDataTextView.text = it
            }
        }
    }
}