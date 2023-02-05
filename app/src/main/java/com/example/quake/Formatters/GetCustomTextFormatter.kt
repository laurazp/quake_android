package com.example.quake.Formatters

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.SpannableStringBuilder
import androidx.core.text.backgroundColor
import androidx.core.text.bold
import androidx.core.text.color
import com.example.quake.R

class GetCustomTextFormatter {

    fun getSimplifiedTitle(titleWithoutFormat: String?, place: String?): String {
        var formattedTitle: String
        if (titleWithoutFormat?.contains(" of ") == true) {
            val delimiter = " of "
            val parts = titleWithoutFormat.split(delimiter)
            if (parts.last() != "") {
                formattedTitle = parts.last()
            } else {
                formattedTitle = "Unknown"
            }
        } else if (titleWithoutFormat?.contains(" - ")!!) {
            val delimiter = " - "
            val parts = titleWithoutFormat.split(delimiter)
            val title: List<String> = parts
            if (title.last() != "") {
                formattedTitle = title.last()
            } else {
                formattedTitle = "Unknown"
            }
        } else {
            if (place != null) {
                formattedTitle = place
            } else {
                formattedTitle = "Unknown"
            }
        }
        return formattedTitle
    }

    fun getSpannableString(label: String, content:String): SpannableStringBuilder {
        val formattedString = SpannableStringBuilder()
            .bold { append(label) }
            .bold { append(": ") }
            .append(content)
        return formattedString
    }

    @SuppressLint("ResourceAsColor")
    fun getSpannableStringWithColor(label: String, content:String, color: Int): SpannableStringBuilder {
        val formattedString = SpannableStringBuilder()
            .bold { append(label) }
            .bold { append(": ") }
            .bold { color(color, { append(content) }) }
        return formattedString
    }

    fun getDepthSpannableString(label: String, content:Float): SpannableStringBuilder {
        val formattedString = SpannableStringBuilder()
            .bold { append(label) }
            .bold { append(": ") }
            .append(String.format("%.2f", content))
            .append("km")
        return formattedString
    }
}