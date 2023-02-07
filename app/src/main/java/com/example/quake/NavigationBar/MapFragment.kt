package com.example.quake.NavigationBar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quake.API.EarthquakesApiDataSource
import com.example.quake.API.Models.Feature
import com.example.quake.Formatters.GetFormattedCoordsFormatter
import com.example.quake.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var featuresList: List<Feature>
    private val getFormattedCoordsFormatter = GetFormattedCoordsFormatter()
    private val earthquakesApiDataSource = EarthquakesApiDataSource()
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        return view
        createFragment()
    }

    private fun createFragment() {
        val mapFragment: SupportMapFragment = parentFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        //addMarkers(this.googleMap)
    }

    private fun addMarkers(googleMap: GoogleMap) {
        featuresList = earthquakesApiDataSource.getData(activity)

        /*val gson = Gson()
        val mPrefs: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val json: String = mPrefs.getString("SelectedFeaturesList", "")!!
        val featuresList: List<Feature> = gson.fromJson(json, Feature::class.java) as List<Feature>*/

        featuresList.forEach { feature ->
            val formattedPosition = getFormattedCoordsFormatter.getFormattedLatLngCoords(feature.geometry.coordinates)
            //TODO: Modificar por datos de EarthquakeModel !!
            val marker = this.googleMap.addMarker(
                MarkerOptions()
                    .title(feature.properties.title)
                    .position(formattedPosition)
            )
        }
    }
}

