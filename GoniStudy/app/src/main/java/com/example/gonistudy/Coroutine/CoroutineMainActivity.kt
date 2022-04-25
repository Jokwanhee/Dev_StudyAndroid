package com.example.gonistudy.Coroutine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityCoroutineMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class CoroutineMainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityCoroutineMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            downloadButton.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    progressBar.visibility = View.VISIBLE
                    val url = inputUrlEditText.text.toString()

                    val bitmap = withContext(Dispatchers.IO) {
                        loadImage(url)
                    }

                    coroutineImageView.setImageBitmap(bitmap)
                    progressBar.visibility = View.INVISIBLE
                }
            }
        }

    }
}

suspend fun loadImage(imageUrl:String):Bitmap{

    // 이미지의 주소를 복사해서 사용하기 위해서는 INTERNET 권한을 허용해야한다.
    val url = URL(imageUrl)
    val stream = url.openStream()

    return BitmapFactory.decodeStream(stream)
}