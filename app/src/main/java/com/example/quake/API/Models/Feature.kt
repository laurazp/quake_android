package com.example.quake.API.Models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlin.Array
import kotlin.Int
import kotlin.arrayOfNulls

data class Feature(
    @SerializedName("properties") var properties: Property,
    @SerializedName("geometry") var geometry: Geometry
): Parcelable {
    @Suppress("UNCHECKED_CAST")
    constructor(parcel: Parcel) : this(
        //parcel.readValue(Property::class.java.classLoader) as Property,
        //parcel.readValue(Geometry::class.java.classLoader) as Geometry
        parcel.readParcelable<Feature>(Property::class.java.classLoader) as Property,
        parcel.readParcelable<Feature>(Geometry::class.java.classLoader) as Geometry
        ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest?.let {
            //dest.writeValue(properties) as Property
            //dest.writeValue(geometry) as Geometry
            dest.writeParcelable(properties, flags)
            dest.writeParcelable(geometry, flags)
            //dest.writeValue(properties) as Property
            //dest.writeValue(geometry) as Geometry
            //dest.writeStringArray(arrayOf((String.valueOf(this.properties)), this.geometry.toString()))
        }
    }

    companion object CREATOR : Parcelable.Creator<Feature> {
        override fun createFromParcel(parcel: Parcel): Feature {
            return Feature(parcel)
        }

        override fun newArray(size: Int): Array<Feature?> {
            return arrayOfNulls(size)
        }
    }
}
