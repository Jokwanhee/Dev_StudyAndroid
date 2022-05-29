package com.goni99.asynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.goni99.asynctask.data.Library

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        loadLibraries()
    }

    fun loadLibraries(){
        var retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var service = retrofit.create(SeoulOpenService::class.java)

        service.getLibraries(SeoulOpenApi.API_KEY, 200)
            .enqueue(object: Callback<Library>{
                override fun onResponse(call: Call<Library>, response: Response<Library>) {
                    val result = response.body()
                    showLibraries(result)
                }

                override fun onFailure(call: Call<Library>, t: Throwable) {
                    Log.e("testingError", "E = ${t.localizedMessage}")
                    Toast.makeText(this@MapsActivity, "데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun showLibraries(result: Library?){
        result?.let {
            val latlngBounds = LatLngBounds.Builder()
            for (library in it.SeoulPublicLibraryInfo.row){
                val position = LatLng(library.XCNTS.toDouble(), library.YDNTS.toDouble())
                var marker = MarkerOptions().position(position).title(library.LBRRY_NAME)
                mMap.addMarker(marker)
                latlngBounds.include(position)
            }
            val bounds = latlngBounds.build()
            val padding = 9

            val camera = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            mMap.moveCamera(camera)
        }
    }
}