package com.example.gonistudy.Permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gonistudy.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPermissionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loadPermissionButton.setOnClickListener {
            checkPermission()
        }
    }

    fun checkPermission(){
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        if (cameraPermission == PackageManager.PERMISSION_GRANTED){
            openCamera()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 99)
    }

    private fun openCamera() {
        Toast.makeText(this, "카메라를 실행합니다.", Toast.LENGTH_SHORT).show()
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode){
            99 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera()
                } else {
                    Toast.makeText(this, "권한을 허용하지 않을 시 앱 종료!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}