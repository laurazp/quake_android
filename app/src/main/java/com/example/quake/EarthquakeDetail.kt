package com.example.quake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call.Details
import android.view.MenuItem

class EarthquakeDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earthquake_detail)

        val display = supportActionBar
        //TODO: Cambiar title por título del terremoto
        display?.title = "Earthquake Detail"
        display?.setDisplayHomeAsUpEnabled(true)
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