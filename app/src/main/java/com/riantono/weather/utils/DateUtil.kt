package com.riantono.weather.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        val FORMAT_DATE_Y_M_D = "yyyy-MM-dd"
        fun formatDate(timestamp: Long): String {
            val simpleDateFormat = SimpleDateFormat(FORMAT_DATE_Y_M_D, Locale.US)
            val date = Date(timestamp * 1000) //  times by 1000 because it's epoch timestamp
            return simpleDateFormat.format(date)
        }
    }
}