package com.example.quake.NavigationBar

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quake.API.APIService
import com.example.quake.API.Models.Feature
import com.example.quake.EarthquakeAdapter
import com.example.quake.EarthquakeDetail
import com.example.quake.R
import com.example.quake.RecyclerViewOnClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment(), /*OnClickListener,*/ RecyclerViewOnClickListener {

    private val featureList = mutableListOf<Feature>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EarthquakeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        getLastEarthquakes()
    }

    private fun initRecyclerView(view: View) {
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvList)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = EarthquakeAdapter(featureList, this)
        recyclerView.adapter = adapter

        //TODO: Set OnClickListener para las cells
        //recyclerView.setOnClickListener(this)

        //To navigate to another fragment (--> EarthquakeDetail?)
        //findNavController().navigate(R.id.fragmentName)

        /*adapter = EarthquakeAdapter(featureList, EarthquakeAdapter.OnClickListener {
            //val intent = Intent(this, EarthquakeDetail::class.java)
            //TODO: Pasar los datos del feature desde aquí??
            //startActivity(intent)
        })*/
    }

    /*override fun onClick(v: View?) {
        TODO("Not yet implemented")
        println("Clicked on a cell!!!")
    }*/

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) // It's important here
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.quake.R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
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
        Toast.makeText(activity, "There was an error.", Toast.LENGTH_SHORT).show()
    }

    override fun recyclerviewClick(position: Int) {
        //Inicio prueba: Carga de Earthquake Detail

        //To navigate to another fragment (--> EarthquakeDetail?)
        //findNavController().navigate(R.id.fragmentName)

        //adapter = EarthquakeAdapter(featureList, EarthquakeAdapter.OnClickListener {
        val intent = Intent (getActivity(), EarthquakeDetail::class.java)
        getActivity()?.startActivity(intent)
        //})
        //Fin prueba: Carga de Earthquake Detail
        adapter.notifyDataSetChanged()
    }

    //TODO: Revisar - Métodos para searchView
    /*override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByDate("", "", 1, 1)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }*/
}




