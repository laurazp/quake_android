package com.example.quake.Formatters

class GetMagnitudeColorFormatter {
    fun getMagnitudeLevel(magnitude: Double): Int {
        var magnitudeLevel: Int
        var magnitudeColor: Int

        if (magnitude < 3) {
            magnitudeLevel = 1
        }
        else if (magnitude >= 3 && magnitude < 5) {
            magnitudeLevel = 2
        }
        else {
            magnitudeLevel = 3
        }



        return magnitudeLevel
    }
}