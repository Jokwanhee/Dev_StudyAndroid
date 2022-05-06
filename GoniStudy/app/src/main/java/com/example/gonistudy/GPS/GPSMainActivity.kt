package com.example.gonistudy.GPS

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.gonistudy.R
import com.example.gonistudy.WeatherAPI.WeatherMainActivity
import com.example.gonistudy.databinding.ActivityGpsMainBinding
import com.google.android.gms.location.*

class GPSMainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityGpsMainBinding.inflate(layoutInflater)
    }

    private var mFusedLocationProviderClient: FusedLocationProviderClient? =
        null // 현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
    internal lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는
    private val REQUEST_PERMISSION_LOCATION = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mLocationRequest = LocationRequest.create().apply {
            this.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        // 버튼 이벤트를 통해 현재 위치 찾기
//        binding.gpsButton.setOnClickListener {
        if (checkPermissionForLocation(this)) {
            startLocationUpdate()
        }
//        }
        binding.goWeatherButton.setOnClickListener {
            val intent = Intent(this, WeatherMainActivity::class.java)
            intent.putExtra("x", mLastLocation.latitude)
            intent.putExtra("y", mLastLocation.longitude)
            startActivity(intent)
        }

    }

    private fun startLocationUpdate() {
        //FusedLocationProviderClient의 인스턴스를 생성.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
        // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청
        mFusedLocationProviderClient!!.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }
    // 시스템으로 부터 위치 정보를 콜백으로 받음

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
            locationResult.lastLocation
            onLocationChanged(locationResult.lastLocation)
        }
    }

    // 시스템으로 부터 받은 위치정보를 화면에 갱신해주는 메소드
    fun onLocationChanged(location: Location) {
        mLastLocation = location
        with(binding) {
            locationY.text = "경도 : " + mLastLocation.latitude
            locationX.text = "위도 : " + mLastLocation.longitude
        }

    }

    // 위치 권한이 있는지 확인하는 메서드

    private fun checkPermissionForLocation(context: Context): Boolean {
        // Android 6.0 Marshmallow 이상에서는 위치 권한에 추가 런타임 권한이 필요

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSION_LOCATION
                )
                false
            }
        } else true
    }

    // 사용자에게 권한 요청 후 결과에 대한 처리 로직

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdate()
            } else {
                Log.d("testing", "onRequestPermissionsResult _ 권한 허용 거부")
                Toast.makeText(this, "onRequestPermissionsResult _ 권한 허용 거부", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


}