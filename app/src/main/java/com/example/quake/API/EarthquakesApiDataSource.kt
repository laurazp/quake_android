package com.example.quake.API

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.quake.API.Models.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EarthquakesApiDataSource {
    private val featureList = mutableListOf<Feature>()

    //TODO: Modificar con parámetros
    fun getData(activity: FragmentActivity?): List<Feature> {
        CoroutineScope(Dispatchers.IO).launch {
            //val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=${current}&endtime=${endDate}&limit=${selectedPageSize}&offset=${actualOffset}")
            val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=2023-01-01&endtime=2023-01-05")
            val features = call.body()

            activity?.runOnUiThread(Runnable {
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    //adapter.notifyDataSetChanged() //TODO: Sólo para recycler view
                } else {
                    showError(activity)
                }
            })
        }
        return featureList
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showError(activity: Activity) {
        Toast.makeText(activity, "There was an error.", Toast.LENGTH_SHORT).show()
    }
}