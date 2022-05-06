package com.example.gonistudy.WeatherAPI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityWeatherMainBinding
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherMainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityWeatherMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val baseUrl = "https://api.openweathermap.org"
        val lat = "35.676880592203375"
        val lon = "139.77413537318625"
//        val lat = intent.getStringExtra("x").toString()
//        val lon = intent.getStringExtra("y").toString()
        val apiKey = "92292a4dfc3d6d24228db2eb16f77ec6"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, apiKey)
        call.enqueue(object: Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200){
                    val weatherResponse = response.body()
                    Log.d("WeatherMain", "result : "+ weatherResponse.toString())
                    var cTemp = weatherResponse!!.main!!.temp - 273.15 // 화씨 -> 섭씨
                    var minTemp = weatherResponse!!.main!!.temp_min - 273.15
                    var maxTemp = weatherResponse!!.main!!.temp_max - 273.15
                    val stringBuilder =
                        "지역: " + weatherResponse!!.sys!!.country + "\n" +
                        "현재 기온: " + cTemp + "\n" +
                        "최저 기온: " + minTemp + "\n" +
                        "최고 기온: " + maxTemp + "\n" +
                        "풍속: " + weatherResponse!!.wind!!.speed + "\n" +
                        "일출시간: " + weatherResponse!!.sys!!.sunrise + "\n" +
                        "일몰시간: " + weatherResponse!!.sys!!.sunset + "\n" +
                        "아이콘: " + weatherResponse!!.weather!!.get(0).icon + "\n"
                    binding.weatherTextView.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("WeatherMain", "result : "+ t.message)
            }
        })
    }

    interface WeatherService{
        @GET("data/2.5/weather")
        fun getCurrentWeatherData(
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("appid") appid: String):
                Call<WeatherResponse>
    }
    class WeatherResponse(){
        @SerializedName("weather") var weather = ArrayList<Weather>()
        @SerializedName("main") var main:Main? = null
        @SerializedName("wind") var wind: Wind? = null
        @SerializedName("sys") var sys: Sys? = null
    }
    class  Weather{
        @SerializedName("id") var id: Int = 0
        @SerializedName("main") var main :String? = null
        @SerializedName("description") var description: String? = null
        @SerializedName("icon") var icon: String? = null
    }
    class Main {
        @SerializedName("temp")
        var temp: Float = 0.toFloat()
        @SerializedName("humidity")
        var humidity: Float = 0.toFloat()
        @SerializedName("pressure")
        var pressure: Float = 0.toFloat()
        @SerializedName("temp_min")
        var temp_min: Float = 0.toFloat()
        @SerializedName("temp_max")
        var temp_max: Float = 0.toFloat()
    }

    class Wind {
        @SerializedName("speed")
        var speed: Float = 0.toFloat()
        @SerializedName("deg")
        var deg: Float = 0.toFloat()
    }

    class Sys{
        @SerializedName("country")
        var country: String? = null
        @SerializedName("sunrise")
        var sunrise: Long = 0
        @SerializedName("sunset")
        var sunset: Long = 0
    }


}