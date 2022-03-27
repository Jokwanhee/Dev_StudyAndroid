package com.example.gonistudy.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.gonistudy.databinding.FragmentDetailBinding

class DetailFragment:Fragment() {

    lateinit var binding:FragmentDetailBinding
    lateinit var fragmentActivity: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActivity) fragmentActivity = context
        Log.d("testing", "D onAttach")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("testing", "D onCreateView")

        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("testing", "D onViewCreated")

        binding.detailFragmentBackButton.setOnClickListener {
            val bundle = bundleOf("senderKey" to "data from Detail Fragment")
            setFragmentResult("request",bundle)
            fragmentActivity.backDetail()

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("testing", "D onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("testing", "D onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("testing", "D onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d("testing", "D onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("testing", "D onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("testing", "D onDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("testing", "D onDetach")

    }
}