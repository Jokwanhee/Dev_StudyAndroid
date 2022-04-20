package com.example.gonistudy.Camera

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gonistudy.BaseActivity.BaseActivity
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityCameraMainBinding
import java.text.SimpleDateFormat

class CameraMainActivity : BaseActivity() {

    val PERM_STORAGE = 9
    val PERM_CAMERA = 10

    val binding by lazy {
        ActivityCameraMainBinding.inflate(layoutInflater)
    }

    // 4. 카메라를 찍은 후에 호출 / 6. 갤러리에서 선택 후 호출
    val startForResultWithCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
//            val bitmap = it.data?.extras?.get("data") as Bitmap // 미리보기 이미지를 가져온다(화질이 낮음)
//            binding.cameraImageView.setImageBitmap(bitmap)
            realUri?.let {
                val bitmap = loadBitmap(it)
                binding.cameraImageView.setImageBitmap(bitmap)

                realUri = null
            }
        }
    }

    val startForResultWithGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            it.data?.data?.let { uri ->
                binding.cameraImageView.setImageURI(uri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 공용저장소 권한이 있는 지 확인
        requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERM_STORAGE)
    }

    fun initViews() {
        // 2. 카메라 요청 시 권한을 먼저 체크하고 승인되었으면 카메라를 연다.
        binding.cameraButton.setOnClickListener {
            requirePermissions(arrayOf(Manifest.permission.CAMERA), PERM_CAMERA)
        }
        // 5. 갤러리 버튼이 클릭 되면 갤러리를 연다.
        binding.galleryButton.setOnClickListener {
            openGallery()
        }
    }

    // 원본 이미지의 주소를 저장할 변수
    var realUri : Uri?  = null

    // 3. 카메라에 찍은 사진을 저장하기위한 Uri를 넘겨준다.
    fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        createImageUri(newFileName(), "image/jpg")?.let { uri ->
            realUri = uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
            startForResultWithCamera.launch(intent)
        }
    }

    fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startForResultWithGallery.launch(intent)
    }

    // 원본 이미지를 저장할 Uri를 MediaStore(데이터베이스)에 생성하는 메서드
    fun createImageUri(filename:String, mimeType:String): Uri?{
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }

    // 파일 이름을 생성하는 메서드
    fun newFileName():String{
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }

    // 원본 이미지를 불러오는 메서드
    fun loadBitmap(photoUri:Uri): Bitmap? {
        try {
            return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) { // 27보다 크면
                val source = ImageDecoder.createSource(contentResolver, photoUri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
            }
        } catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }

    override fun permissionGranted(requestCode: Int) {
        when (requestCode) {
            PERM_STORAGE -> initViews()
            PERM_CAMERA -> openCamera()
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when (requestCode) {
            PERM_STORAGE -> {
                Toast.makeText(this, "공용 저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            PERM_CAMERA -> {
                Toast.makeText(this, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }

}