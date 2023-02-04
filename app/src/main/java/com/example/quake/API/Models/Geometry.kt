package com.example.quake.API.Models

import android.os.Parcel
import android.os.Parcelable
import androidx.core.os.ParcelCompat.readArrayList
import com.google.gson.annotations.SerializedName


data class Geometry(
    @SerializedName("coordinates") var coordinates: FloatArray?
    ) /*: Parcelable {
    constructor(parcel: Parcel) : this(
        coordinates = parcel.createStringArrayList()
        //parcel.readArrayList(Float::class.java.classLoader, Float::class.java)
        //parcel.readSerializable(Float::class.java.classLoader, Float::class.java) as ArrayList<Float>
        //parcel.readValue(ArrayList::class.java.classLoader) as ArrayList<Float>
        //parcel.readList(ArrayList<Float>(), ArrayList::class.java.classLoader, Float::class.java) as ArrayList<Float>
        //parcel.readList(List<Float>, Float::class.java.classLoader, Float::class.java) as List<Float>
        //parcel.readList(coordinates, Geometry.class.getClassLoader()) as List<Float>
        //parcel.readList(coordinates, Geometry::class.java.getClassLoader())
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest?.let {
            dest.writeStringList(coordinates)
            //dest.writeSerializable(coordinates) as? ArrayList<Float>
            //dest.writeValue(coordinates) as ArrayList<Float>
            //dest.writeList(coordinates)
        }
    }

    companion object CREATOR : Parcelable.Creator<Geometry> {
        override fun createFromParcel(parcel: Parcel): Geometry {
            return Geometry(parcel)
        }

        override fun newArray(size: Int): Array<Geometry?> {
            return arrayOfNulls(size)
        }
    }
}*/ : Parcelable {
    constructor(parcel: Parcel) : this(
        coordinates = parcel.createFloatArray()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //coordinates = parcel.createFloatArray()
        parcel.writeFloatArray(coordinates)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Geometry> {
        override fun createFromParcel(parcel: Parcel): Geometry {
            return Geometry(parcel)
        }

        override fun newArray(size: Int): Array<Geometry?> {
            return arrayOfNulls(size)
        }
    }
}

