package com.example.quake.API.Models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.Date

data class Property (
    @SerializedName("mag") var mag: Double?,
    @SerializedName("place") var place: String?,
    //@SerializedName("time") var time: Date?, //TODO: Search for date/time in Android
    @SerializedName("tsunami") var tsunami: Int?,
    @SerializedName("title") var title: String?
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest?.let {
            dest.writeValue(mag) as? Double
            dest.writeString(place)
            dest.writeInt(tsunami!!)
            dest.writeString(title)
        }
    }

    companion object CREATOR : Parcelable.Creator<Property> {
        override fun createFromParcel(parcel: Parcel): Property {
            return Property(parcel)
        }

        override fun newArray(size: Int): Array<Property?> {
            return arrayOfNulls(size)
        }
    }
}
