package com.example.quake.API.Models

import com.google.gson.annotations.SerializedName

data class Feature (
    @SerializedName("properties") var properties: Property,
    @SerializedName("geometry") var geometry: Geometry
)
