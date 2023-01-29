package com.example.quake

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.quake.API.APIService
import com.example.quake.API.Models.Feature
import com.example.quake.TabBar.TabPageAdapter
import com.example.quake.databinding.ActivityEarthquakesBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_earthquakes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        setUpTabBar()
        getLastEarthquakes()
    }

    private fun initRecyclerView() {
        earthquakeAdapter = EarthquakeAdapter(featureList, EarthquakeAdapter.OnClickListener {
            val intent = Intent(this, EarthquakeDetail::class.java)
            //TODO: Pasar los datos del feature desde aquí??
            startActivity(intent)
        })
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = earthquakeAdapter
    }

    private fun setUpTabBar() {
        val tabPageAdapter = TabPageAdapter(this, tabLayout.tabCount)
        viewPager.adapter = tabPageAdapter

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLastEarthquakes() {
        CoroutineScope(Dispatchers.IO).launch {

            //val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=${current}&endtime=${endDate}&limit=${selectedPageSize}&offset=${actualOffset}")
            val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=2023-01-01&endtime=2023-01-05")

            val features = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    earthquakeAdapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun searchByDate(startTime: String, endTime: String, selectedPageSize: Int, actualOffset: Int) {
        CoroutineScope(Dispatchers.IO).launch {

            // Esta es la defitiva - No borrar !! --> val call: Response<EarthquakeResponse> = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=${startTime}&endtime=${endTime}&limit=${selectedPageSize}&offset=${actualOffset}")
            val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=2023-01-01&endtime=2023-01-05")

            val features = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    earthquakeAdapter.notifyDataSetChanged()
                } else {
                    showError()
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
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}


