package com.example.gonistudy.SharedPreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivitySharedPreferenceMainBinding

// Device File Explorer / data / data / com.example.gonistudy
// shared_prefs / key_first_open.xml 생성됨을 확인할 수 있다.
// SharedPreference 를 사용하여 앱 첫 화면에서 띄우는 그림을 처음에만 볼 수 있고 나중에는 볼 수 없다.


class SharedPreferenceMainActivity : AppCompatActivity() {

    companion object {
        const val KEY_FIRST_OPEN = "key_first_open"
    }

    val binding by lazy {
        ActivitySharedPreferenceMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val shared = getSharedPreferences(KEY_FIRST_OPEN, Context.MODE_PRIVATE)

        val firstOpen = shared.getBoolean(KEY_FIRST_OPEN, false)
        if (firstOpen) binding.androidImageView.visibility = View.GONE

        val editor = shared.edit()
        editor.putBoolean(KEY_FIRST_OPEN, true)
        editor.commit()
    }
}