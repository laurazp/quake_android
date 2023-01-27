package com.example.quake.API.Models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Property (
    @SerializedName("mag") var mag: Double?,
    @SerializedName("place") var place: String?,
    @SerializedName("time") var time: Date?, //TODO: Search for date/time in Android
    @SerializedName("tsunami") var tsunami: Int?,
    @SerializedName("title") var title: String?
    )
