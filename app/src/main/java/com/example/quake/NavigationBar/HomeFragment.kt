package com.example.quake.NavigationBar

import android.os.Bundle
import android.view.*
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quake.API.APIService
import com.example.quake.API.Models.Feature
import com.example.quake.EarthquakeAdapter
import com.example.quake.R
import com.example.quake.databinding.ActivityEarthquakesBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    //private lateinit var binding: ActivityEarthquakesBinding
    //private lateinit var earthquakeAdapter: EarthquakeAdapter
    private val featureList = mutableListOf<Feature>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EarthquakeAdapter
    //private lateinit var binding: ActivityEarthquakesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(com.example.quake.R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvList)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = EarthquakeAdapter(featureList)
        recyclerView.adapter = adapter

        //TODO: Pruebas
        //binding = ActivityEarthquakesBinding.inflate(layoutInflater)
        //layout.setContentView(this.binding.root)
        /*binding.searchView.setOnQueryTextListener(this)*/
        initRecyclerView()

        getLastEarthquakes()

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) // It's important here
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.quake.R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initRecyclerView() {
        /*adapter = EarthquakeAdapter(featureList, EarthquakeAdapter.OnClickListener {
            val intent = Intent(this, EarthquakeDetail::class.java)
            //TODO: Pasar los datos del feature desde aquí??
            startActivity(intent)
        })*/
        rvList.layoutManager = LinearLayoutManager(context)
        rvList.adapter = adapter
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
            /*runOnUiThread {
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }*/
            activity?.runOnUiThread(Runnable {
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            })
        }
    }

    private fun searchByDate(startTime: String, endTime: String, selectedPageSize: Int, actualOffset: Int) {
        CoroutineScope(Dispatchers.IO).launch {

            // Esta es la defitiva - No borrar !! --> val call: Response<EarthquakeResponse> = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=${startTime}&endtime=${endTime}&limit=${selectedPageSize}&offset=${actualOffset}")
            val call = getRetrofit().create(APIService::class.java).getEarthquakes("query?format=geojson&starttime=2023-01-01&endtime=2023-01-05")

            val features = call.body()

            /*
            runOnUiThread {
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
             */
            activity?.runOnUiThread(Runnable {
                if (call.isSuccessful) {
                    val features: List<Feature> = features?.features ?: emptyList()
                    featureList.clear()
                    featureList.addAll(features)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            })


        }
    }

    private fun showError() {
        //Toast.makeText(this, "There was an error.", Toast.LENGTH_SHORT).show()
        println("THERE WAS AN ERROR.")
    }

    /*override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            //TODO: Revisar
            searchByDate("", "", 1, 1)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }*/
}

private fun CoroutineScope.runOnUiThread(function: () -> Unit) {
    //TODO: Revisar !!
}


// To navigate to another fragment
//findNavController().navigate(R.id.fragmentName)



