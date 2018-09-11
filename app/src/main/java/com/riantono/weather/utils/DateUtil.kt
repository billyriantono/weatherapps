package com.riantono.weather.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        val FORMAT_DATE_Y_M_D = "yyyy-MM-dd"
        val FORMAT_DATE_Y_M_D_H_M_S = "yyyy-MM-dd hh:mm:ss"
        fun formatDate(timestamp: Long): String {
            val simpleDateFormat = SimpleDateFormat(FORMAT_DATE_Y_M_D_H_M_S, Locale.US)
            val date = Date(timestamp * 1000) //  times by 1000 because it's epoch timestamp
            return simpleDateFormat.format(date)
        }
    }
}