package com.example.gonistudy.BaseActivity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityMainBaseBinding
import java.util.jar.Manifest

class MainBaseActivity : BaseActivity() {

    val binding by lazy{
        ActivityMainBaseBinding.inflate(layoutInflater)
    }

    val P_CAMERA = arrayOf(android.Manifest.permission.CAMERA)
    val P_STORAGE = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    val REQ_STORAGE = 99
    val REQ_CAMERA = 100

    val TAKE_CAMERA = 101

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            when (it.resultCode){
                TAKE_CAMERA -> {
                    binding.textView.text = "사진 촬영 찰칵"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requirePermissions(P_STORAGE, REQ_STORAGE)
        binding.button.setOnClickListener {
            requirePermissions(P_CAMERA, REQ_CAMERA)
        }
    }

    override fun permissionGranted(requestCode: Int) {
        when (requestCode){
            REQ_STORAGE -> {
                Toast.makeText(this, "스토리지 권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
            }
            REQ_CAMERA -> {
                Toast.makeText(this, "카메라 권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
                openCamera()
            }
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when (requestCode){
            REQ_STORAGE -> {
                Toast.makeText(this, "스토리지 권한이 없습니다.\n 앱 종료", Toast.LENGTH_SHORT).show()
                finish()
            }
            REQ_CAMERA -> {
                Toast.makeText(this, "카메라 권한이 거절되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startForResult.launch(intent)
    }
}