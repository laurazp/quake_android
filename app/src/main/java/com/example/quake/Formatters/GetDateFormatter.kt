package com.example.quake.Formatters

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class GetDateFormatter {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateToZonedDateTime(dateToFormat: Long): ZonedDateTime? {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(dateToFormat), ZoneId.systemDefault());
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateToStringWithGmt(dateToFormat: Long): String? {
        val formattedDate = ZonedDateTime.ofInstant(Instant.ofEpochMilli(dateToFormat), ZoneId.systemDefault())
        return DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss z").format(formattedDate.withZoneSameInstant(ZoneId.systemDefault()))
    }
}
