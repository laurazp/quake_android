package com.example.quake.Formatters

class GetSimplifiedTitleFormatter {

    fun getSimplifiedTitle(titleWithoutFormat: String?, place: String): String {
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
            formattedTitle = place
        }
        return formattedTitle
    }
}