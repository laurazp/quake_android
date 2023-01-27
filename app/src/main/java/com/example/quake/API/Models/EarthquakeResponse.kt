package com.example.quake.API.Models

import com.google.gson.annotations.SerializedName

data class EarthquakeResponse (
    @SerializedName("features") var features: List<Feature>
    )
