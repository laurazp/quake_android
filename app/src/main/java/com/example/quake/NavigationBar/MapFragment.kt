package com.example.quake.NavigationBar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.quake.API.EarthquakesApiDataSource
import com.example.quake.API.Models.Feature
import com.example.quake.Formatters.GetFormattedCoordsFormatter
import com.example.quake.R
import com.example.quake.databinding.FragmentMapBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var featuresList: List<Feature>
    private val getFormattedCoordsFormatter = GetFormattedCoordsFormatter()
    private val earthquakesApiDataSource = EarthquakesApiDataSource()
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)
        val view: View = binding.root
        //val view = inflater.inflate(R.layout.fragment_map, container, false)

        createFragment()

        // Initialize map fragment
        /*val supportMapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        // Async map
        supportMapFragment.getMapAsync(OnMapReadyCallback() {
            @Override
            fun onMapReady(googleMap: GoogleMap) {
                // When map is loaded
                googleMap.setOnMapClickListener(GoogleMap.OnMapClickListener() {
                    @Override
                    fun onMapClick(latLng: LatLng) {
                        // When clicked on map
                        // Initialize marker options
                        val markerOptions: MarkerOptions = MarkerOptions()
                        // Set position of marker
                        markerOptions.position(latLng);
                        // Set title of marker
                        markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude.toString())
                        // Remove all marker
                        googleMap.clear()
                        // Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f))
                        // Add marker on map
                        googleMap.addMarker(markerOptions)
                    }
                })
            }
        })*/

        return view
    }

    private fun createFragment() {
        val supportMapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        //val mapFragment: SupportMapFragment = parentFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        //mapFragment.getMapAsync(this)
        supportMapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //this.googleMap = googleMap
        addMarkers(googleMap)
        /*val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Sydney")
        )*/
    }

    private fun addMarkers(googleMap: GoogleMap) {
        //featuresList = earthquakesApiDataSource.getData(activity)

        val gson = Gson()
        val mPrefs: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val json: String = mPrefs.getString("SelectedFeaturesList", "")!!
        //val featuresList: List<Feature> = gson.fromJson(json, Feature::class.java) as List<Feature>
        val collectionType: Type = object : TypeToken<Collection<Feature?>?>() {}.type
        val featuresList: Collection<Feature> = gson.fromJson(json, collectionType)

        featuresList.forEach { feature ->
            val formattedPosition = getFormattedCoordsFormatter.getFormattedLatLngCoords(feature.geometry.coordinates)
            //TODO: Modificar por datos de EarthquakeModel !!
            googleMap.addMarker(
                MarkerOptions()
                    .position(formattedPosition)
                    .title(feature.properties.title)
            )
        }
    }
}

