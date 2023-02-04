package com.example.quake.API.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geometry(
    @SerializedName("coordinates") var coordinates: List<Float>
    ) : Parcelable

