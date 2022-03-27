package com.example.gonistudy.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.gonistudy.R
import com.example.gonistudy.databinding.FragmentListBinding

class ListFragment:Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var fragmentActivity: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActivity) fragmentActivity = context
        Log.d("testing", "L onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("testing", "L onCreateView")
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("testing", "L onViewCreated")

        with(binding){
            arguments?.apply {
                binding.keyTextView.text = getString("key")
                binding.valueTextView.text = getInt("value").toString()
            }
        }


        binding.listFragmentGoButton.setOnClickListener {
            setFragmentResultListener("request"){ key, bundle ->
                bundle.getString("senderKey").let {
                    binding.listFragmentTextView.text = it
                }
            }

            fragmentActivity.goDetail()
        }
    }

    fun sendData(data:String){
        binding.fromActivityDataTextView.text = data
    }

    override fun onStart() {
        super.onStart()
        Log.d("testing", "L onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("testing", "L onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("testing", "L onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d("testing", "L onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("testing", "L onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("testing", "L onDestroy")

    }



    override fun onDetach() {
        super.onDetach()
        Log.d("testing", "L onDetach")

    }
}