package com.example.quake

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.quake.API.Models.Feature
import com.example.quake.Formatters.GetCustomTextFormatter
import java.util.*

class EarthquakeDetail : AppCompatActivity() {

    private val textFormatter = GetCustomTextFormatter()
    /*private var title: String? = "Unknown"
    private var place: String? = "Unknown"
    private lateinit var time: Date
    private var tsunami: Int = 0
    private lateinit var coords: String
    private var depth: String? = "Unknown"
    private var magnitude: Double? = 0.0*/

    //View Labels
    private lateinit var placeLabel: TextView
    private lateinit var dateLabel: TextView
    private lateinit var tsunamiLabel: TextView
    private lateinit var coordsLabel: TextView
    private lateinit var depthLabel: TextView
    private lateinit var magnitudeLabel: TextView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earthquake_detail)

        //TODO: Es mejor implementar opciones según el SDK??
        //Get selected feature data from HomeFragment
        val selectedFeature = intent.getParcelableExtra("SELECTED_FEATURE", Feature::class.java)

        configureUpperActionBar(selectedFeature!!)
        initializeViewLabels()
        bindEarthquake(selectedFeature)
    }

    private fun configureUpperActionBar(selectedFeature: Feature) {
        val display = supportActionBar
        display?.setDisplayHomeAsUpEnabled(true) //Show "Back" arrow
        val formattedTitle = textFormatter.getSimplifiedTitle(selectedFeature?.properties?.title, selectedFeature?.properties?.place)
        display?.title = formattedTitle
    }

    private fun initializeViewLabels() {
        placeLabel = findViewById(R.id.cellPlace)
        dateLabel = findViewById(R.id.cellDate)
        tsunamiLabel = findViewById(R.id.cellTsunami)
        coordsLabel = findViewById(R.id.cellCoords)
        depthLabel = findViewById(R.id.cellDepth)
        magnitudeLabel = findViewById(R.id.cellMagnitude)
    }

    private fun bindEarthquake(feature: Feature) {
        var tsunamiValue = ""
        if (feature.properties.tsunami == 0) {
            tsunamiValue = "No"
        } else {
            tsunamiValue = "Yes"
        }

        placeLabel.text = textFormatter.getSpannableString("Place", feature.properties.place!!)
        //dateLabel.text = textFormatter.getSpannableString("Date", feature.properties.time!!)
        tsunamiLabel.text = textFormatter.getSpannableString("Tsunami", tsunamiValue) //"Tsunami: $tsunamiValue"
        //coordsLabel.text = "Coords: " + [feature.geometry.coordinates[0], feature.geometry.coordinates[1]]
        depthLabel.text = textFormatter.getSpannableString("Depth", feature.geometry.coordinates[2].toString()) //TODO: + "km"
        magnitudeLabel.text = textFormatter.getSpannableString("Magnitude", feature.properties.mag.toString())
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
}