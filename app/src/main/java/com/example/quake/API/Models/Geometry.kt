package com.example.quake.API.Models

import com.google.gson.annotations.SerializedName

data class Geometry (
    @SerializedName("coordinates") var coordinates: List<Float>
    )

