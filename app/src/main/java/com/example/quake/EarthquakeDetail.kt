package com.example.quake

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.quake.Formatters.GetSimplifiedTitleFormatter
import java.util.*

class EarthquakeDetail : AppCompatActivity() {

    private val titleFormatter = GetSimplifiedTitleFormatter()
    private var title: String? = "Unknown"
    private var place: String? = "Unknown"
    private lateinit var time: Date
    private var tsunami: Int = 0
    private lateinit var coords: ArrayList<Int>
    private var depth: Float = 0f
    private var magnitude: Double? = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earthquake_detail)

        //TODO: Get feature data from HomeFragment
        val extras = intent.extras
        this.title = extras?.getString("EARTHQUAKE_TITLE")

        val display = supportActionBar
        val formattedTitle = titleFormatter.getSimplifiedTitle(this.title, "Place")
        display?.title = formattedTitle
        display?.setDisplayHomeAsUpEnabled(true)

        bindEarthquake()
    }

    private fun bindEarthquake() {
        //TODO: Configurar datos


        /*val fragment = HomeFragment()
        fragment.listener = { feature ->
            this.title = feature.properties.title
            this.place = feature.properties.place
            //this.time = feature.properties.time
            this.tsunami = feature.properties.tsunami!!
            //this.coords =[feature.geometry.coordinates[0], feature.geometry.coordinates[1]]
            //this.depth = feature.properties.depth
            this.magnitude = feature.properties.mag
            print(this.title)
        }*/

        /*this.title = title
        this.place = place
        this.time = time
        this.tsunami = tsunami
        this.coords = coords
        this.depth = depth
        this.magnitude = magnitude*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*private var title: String
    private var place: String?
    private var time: Date
    private var tsunami: Int
    private var coords: ArrayList<Int>
    private var depth: Float
    private var magnitude: Double?

    constructor(
        title: String,
        place: String?,
        time: Date,
        tsunami: Int,
        coords: ArrayList<Int>,
        depth: Float,
        magnitude: Double?
    ) {
        this.title = title
        this.place = place
        this.time = time
        this.tsunami = tsunami
        this.coords = coords
        this.depth = depth
        this.magnitude = magnitude
    }*/

}