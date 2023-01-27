package com.example.quake.API

import com.example.quake.API.Models.EarthquakeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getEarthquakes(@Url url:String): Response<EarthquakeResponse>
}