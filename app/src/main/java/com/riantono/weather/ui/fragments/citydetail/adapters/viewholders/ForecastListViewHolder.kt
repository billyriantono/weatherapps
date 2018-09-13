package com.riantono.weather.ui.fragments.citydetail.adapters.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.riantono.weather.R
import com.riantono.weather.ui.entity.ForecastModel
import java.text.SimpleDateFormat
import java.util.*

val FORMAT_DATE_D_M_Y_H_M_S = "dd MMMM yyyy HH:mm:ss"

fun formatDate(timestamp: Long): String {
    val simpleDateFormat = SimpleDateFormat(FORMAT_DATE_D_M_Y_H_M_S, Locale.US)
    val date = Date(timestamp * 1000) //  times by 1000 because it's epoch timestamp
    return simpleDateFormat.format(date)
}

class ForecastListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val tvWeatherDegree = itemView?.findViewById<TextView>(R.id.tv_weather_degree)
    val tvWeatherDate = itemView?.findViewById<TextView>(R.id.tv_date)


    fun bindData(context: Context, forecastModel: ForecastModel) {
        tvWeatherDegree?.text = context.resources.getString(R.string.degree_placeholder, forecastModel.temp.toString())
        if (forecastModel.dt != null) {
            tvWeatherDate?.text = formatDate(forecastModel.dt)
        } else {
            tvWeatherDate?.text = "Unknown Date"
        }
    }
}