package com.example.quake

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.quake.API.Models.Feature
import com.example.quake.Formatters.GetCustomTextFormatter
import com.example.quake.Formatters.GetDateFormatter
import com.example.quake.Formatters.GetFormattedCoordsFormatter
import com.example.quake.Formatters.GetMagnitudeColorFormatter
import java.util.*

class EarthquakeDetail : AppCompatActivity() {

    private val textFormatter = GetCustomTextFormatter()
    private val getDateFormatter = GetDateFormatter()
    private val getMagnitudeColorFormatter = GetMagnitudeColorFormatter()
    private val getFormattedCoordsFormatter = GetFormattedCoordsFormatter()

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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindEarthquake(feature: Feature) {
        //Place Label
        if (feature.properties.place != null){
            placeLabel.text = textFormatter.getSpannableString("Place", feature.properties.place!!)
        } else {
            placeLabel.text = textFormatter.getSpannableString("Place", "Unknown")
        }
        //Date Label
        val formattedTime = getDateFormatter.formatDateToStringWithGmt(feature.properties.time!!)
        dateLabel.text = textFormatter.getSpannableString("Date", formattedTime.toString())
        //Tsunami Label
        var tsunamiValue = ""
        if (feature.properties.tsunami == 0) {
            tsunamiValue = "No"
        } else {
            tsunamiValue = "Yes"
        }
        tsunamiLabel.text = textFormatter.getSpannableString("Tsunami", tsunamiValue) //"Tsunami: $tsunamiValue"
        //Coords Label
        val coordsString = getFormattedCoordsFormatter.getFormattedCoords(feature.geometry.coordinates)
        coordsLabel.text = textFormatter.getSpannableString("Coords", coordsString)
        //Depth Label //TODO: + "km"
        depthLabel.text = textFormatter.getDepthSpannableString("Depth", feature.geometry.coordinates[2])
        //Magnitude Label
        var magnitudeLevel = getMagnitudeColorFormatter.getMagnitudeLevel(feature.properties.mag!!)
        magnitudeLabel.text = textFormatter.getSpannableStringWithColor("Magnitude", (feature.properties.mag).toString(), magnitudeLevel)
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