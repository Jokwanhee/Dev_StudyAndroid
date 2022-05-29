package com.goni99.googlemaps

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)

        val descriptor = getDescriptorBitmap(R.drawable.marker)

        // 마커
        val marker = MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney")
            .icon(descriptor)
        mMap.addMarker(marker)

        val camera = CameraUpdateFactory.newLatLng(sydney)
        // 줌 사용 시
        val cameraZoom = CameraPosition.Builder()
            .target(sydney)
            .zoom(12f) // float 값 높아질 수록 zoom in 낮아질 수록 zoom out
            .build()
        val cameraOption = CameraUpdateFactory.newCameraPosition(cameraZoom)

        mMap.moveCamera(cameraOption)
    }


    // 마커 아이콘 제작
    fun getDescriptorBitmap(drawableId: Int): BitmapDescriptor{
        var bitmapDrawable: BitmapDrawable
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            bitmapDrawable = getDrawable(drawableId) as BitmapDrawable
        } else {
            bitmapDrawable = resources.getDrawable(drawableId) as BitmapDrawable
        }
        // 마커 아이콘 크기 제작
        val scaledBitmap = Bitmap.createScaledBitmap(bitmapDrawable.bitmap, 100, 100 ,false)
        return BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    }
}