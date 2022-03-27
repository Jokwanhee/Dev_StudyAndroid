package com.example.gonistudy.Fragment.Data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gonistudy.R

class FragmentActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment2)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.sender_fragment, SenderFragment())
            .add(R.id.receiver_fragment, ReceiverFragment())
            .commit()
    }
}