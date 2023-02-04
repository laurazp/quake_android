package com.example.quake.Formatters

class GetFormattedCoordsFormatter {

    fun getFormattedCoords(actualCoords: List<Float>): String {
        val longitude = actualCoords?.get(0)
        val latitude = actualCoords?.get(1)
        val longitudeString: String
        val latitudeString: String

        if (longitude!! < 0) {
            longitudeString = (-(longitude )).toString() + "W"
        } else {
            longitudeString = (longitude).toString() + "E"
        }

        if (latitude!! < 0) {
            latitudeString = (-(latitude )).toString() + "S"
        } else {
            latitudeString = (latitude ).toString() + "N"
        }

        return "$longitudeString, $latitudeString"
    }
}