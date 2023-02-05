package com.example.quake.Formatters

import android.graphics.Color

class GetMagnitudeColorFormatter {
    private val textFormatter = GetCustomTextFormatter()

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

        when (magnitudeLevel) {
            1 -> magnitudeColor = Color.parseColor("#00FF00")
            2 -> magnitudeColor = Color.parseColor("#FFA500")
            3 -> magnitudeColor = Color.parseColor("#FF0000")
            else -> {
                magnitudeColor = Color.parseColor("#0000FF")
            }
        }

        return magnitudeColor
    }
}