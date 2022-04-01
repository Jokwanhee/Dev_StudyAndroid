package com.example.gonistudy.BaseActivity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

// abstract -> 추상화 : 다른 클래스에서 상속을 할 수 있다. 하지만 인스턴스화 할 수 없다.
// open


abstract class BaseActivity : AppCompatActivity() {

    abstract fun permissionGranted(requestCode: Int)
    abstract fun permissionDenied(requestCode: Int)

    // 권한 검사
    fun requirePermissions(permissions: Array<String>, requestCode:Int){
        // api 버전 마시멜로우 이하이면 권한처리가 필요 없다.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            permissionGranted(requestCode)
        } else {
            // 권한 없으면 권한 요청 -> 팝업
            ActivityCompat.requestPermissions(this, permissions , requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }){
            permissionGranted(requestCode)
        } else {
            permissionDenied(requestCode)
        }
    }

}