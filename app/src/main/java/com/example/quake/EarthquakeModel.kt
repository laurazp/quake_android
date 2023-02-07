package com.example.quake

import com.google.android.gms.maps.model.LatLng

class EarthquakeModel(
    private var fullTitle: String,
    private var simplifiedTitle: String,
    private var place: String,
    private var formattedCoords: String,
    private var originalCoords: LatLng,
    private var depth: String,
    private var date: String,
    private var originalDate: Long,
    private var tsunami: String,
    private var magnitude: String
) {

}