package com.example.quake.API.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Property (
    @SerializedName("mag") var mag: Double?,
    @SerializedName("place") var place: String?,
    //@SerializedName("time") var time: Date?, //TODO: Search for date/time in Android
    @SerializedName("tsunami") var tsunami: Int?, //TODO: Pasar a byte?
    @SerializedName("title") var title: String?
    ) : Parcelable
