package com.goni99.asynctask

import com.goni99.asynctask.data.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object {
        val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        val API_KEY = "434b675a4d6a6f6e33355959615652"
    }

}

interface SeoulOpenService {
    @GET("{api_key}/json/SeoulPublicLibraryInfo/1/{end}")
    fun getLibraries(@Path("api_key") key: String, @Path("end") limit: Int) : Call<Library>
}