package com.example.quake.API.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feature(
    @SerializedName("properties") var properties: Property,
    @SerializedName("geometry") var geometry: Geometry
): Parcelable

