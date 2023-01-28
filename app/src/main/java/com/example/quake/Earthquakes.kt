package com.example.quake

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quake.API.APIService
import com.example.quake.API.Models.Feature
import com.example.quake.API.Models.Geometry
import com.example.quake.API.Models.Property
import com.example.quake.databinding.ActivityEarthquakesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Earthquakes : AppCompatActivity(), OnQueryTextListener {

    private lateinit var binding: ActivityEarthquakesBinding
    private lateinit var earthquakeAdapter: EarthquakeAdapter
    private val featureList = mutableListOf<Feature>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchView.setOnQueryTextListener(this)
        initRecyclerView()
        getLastEarthquakes()
    }

    private fun initRecyclerView() {
        earthquakeAdapter = EarthquakeAdapter(featureList)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = earthquakeAdapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLastEarthquakes() {
        println("Dentro de getLastEarthquakes")
        CoroutineScope(Dispatchers.IO).launch {
            println("Dentro de la corrutina")

            //val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=${current}&endtime=${endDate}&limit=${selectedPageSize}&offset=${actualOffset}")
            val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=2023-01-01&endtime=2023-01-05")
            println("call OK")

            val features = call.body()
            runOnUiThread {
                println("Dentro del runOnUiThread")
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    earthquakeAdapter.notifyDataSetChanged()
                    println("API call successfull")
                } else {
                    showError()
                    println("API error")
                }
            }
        }
    }

    private fun searchByDate(startTime: String, endTime: String, selectedPageSize: Int, actualOffset: Int) {
        println("Dentro de searchByDate")
        CoroutineScope(Dispatchers.IO).launch {
            println("Dentro de la corrutina")

            // Esta es la defitiva - No borrar !! --> val call: Response<EarthquakeResponse> = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=${startTime}&endtime=${endTime}&limit=${selectedPageSize}&offset=${actualOffset}")
            val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=2023-01-01&endtime=2023-01-05")
            println("call OK")

            val features = call.body()
            runOnUiThread {
                println("Dentro del runOnUiThread")
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    earthquakeAdapter.notifyDataSetChanged()
                    println("API call successfull")
                } else {
                    showError()
                    println("API error")
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "There was an error.", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            //TODO: Revisar
            searchByDate("", "", 1, 1)
            println("TODO: REVISAR!")
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}


