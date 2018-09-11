package com.riantono.weather.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        val FORMAT_DATE_D_M_Y_H_M_S = "dd MMMM yyyy HH:mm:ss"
        fun formatDate(timestamp: Long): String {
            val simpleDateFormat = SimpleDateFormat(FORMAT_DATE_D_M_Y_H_M_S, Locale.US)
            val date = Date(timestamp * 1000) //  times by 1000 because it's epoch timestamp
            return simpleDateFormat.format(date)
        }
    }
}